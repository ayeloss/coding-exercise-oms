package coding.exercise.oms.service;

import coding.exercise.oms.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OrderService {

    private AggregationService aggregationService;
    private ExecutorService threadPoolExecutor;

    public CompletableFuture submitOrders(List<Order> orders) {
        List<CompletableFuture> list = new ArrayList<>();
        for (Order order : orders) {
            Runnable r = () -> aggregationService.receiveOrder(order);

            CompletableFuture cf = CompletableFuture.runAsync(r, threadPoolExecutor);

            list.add(cf);
        }

        CompletableFuture cf = CompletableFuture.allOf(list.stream().toArray(CompletableFuture[]::new));

        return cf;

    }

    public void setAggregationService(AggregationService aggregationService) {
        this.aggregationService = aggregationService;
    }

    public void setThreadPoolExecutor(ExecutorService threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }
}
