package coding.exercise.oms.service;

import coding.exercise.oms.repository.AggregatedOrderRepository;
import coding.exercise.oms.order.AggregatedOrder;
import coding.exercise.oms.order.Direction;
import coding.exercise.oms.order.Order;

public class AggregationService {

    private AggregatedOrderRepository aggregatedOrderRepository;


    public synchronized void receiveOrder(Order order) {
        String type = order.getType();
        // Lock lock = aggregatedOrderRepository.findLockByType(type)
        // lock.lock()

        AggregatedOrder aggOrder = aggregatedOrderRepository.findByType(type);
        if (aggOrder==null) {
            aggOrder = new AggregatedOrder();
            aggOrder.setType(order.getType());
            aggOrder.setQuantity(0);

        }

        int currAggQuantity = aggOrder.getQuantity();
        int quantityChange = evaluateQuantityChange(order);
        aggOrder.setQuantity(currAggQuantity + quantityChange);

        aggregatedOrderRepository.addOrUpdateOrder(aggOrder);
    }

    public int evaluateQuantityChange(Order order) {
        int quantityChange = order.getDirection()==Direction.BUY ? order.getQuantity() : -order.getQuantity();
        return quantityChange;
    }


    public void setAggregatedOrderRepository(AggregatedOrderRepository aggregatedOrderRepository) {
        this.aggregatedOrderRepository = aggregatedOrderRepository;
    }
}
