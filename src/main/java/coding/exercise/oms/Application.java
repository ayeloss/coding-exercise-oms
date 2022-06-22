package coding.exercise.oms;


import coding.exercise.oms.repository.AggregatedOrderRepository;
import coding.exercise.oms.order.AggregatedOrder;
import coding.exercise.oms.order.Direction;
import coding.exercise.oms.order.Order;
import coding.exercise.oms.service.AggregationService;
import coding.exercise.oms.service.OrderService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {





	public static void main(String[] args) throws InterruptedException {
		AggregatedOrderRepository repository = new AggregatedOrderRepository();
		AggregationService aggregationService = new AggregationService();
		aggregationService.setAggregatedOrderRepository(repository);

		ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);

		OrderService orderService = new OrderService();
		orderService.setAggregationService(aggregationService);
		orderService.setThreadPoolExecutor(threadPoolExecutor);



		List<Order> list = Arrays.asList(new Order(2, "ibm", Direction.BUY),
				new Order(1, "ibm", Direction.SELL),
				new Order(1, "fx", Direction.BUY),
				new Order(10, "ibm", Direction.SELL)
				);

		CompletableFuture cf = orderService.submitOrders(list);

		cf.whenComplete((t, u) -> {
			Map<String, AggregatedOrder> result = repository.retrieiveOrder();

			for (Map.Entry<String, AggregatedOrder> entry : result.entrySet()) {
				System.out.println("type:" + entry.getKey() + ", order:" + entry.getValue());
			}

			System.out.println("finished");

			threadPoolExecutor.shutdown();
		});



	}

}
