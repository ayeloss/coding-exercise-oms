package coding.exercise.oms.repository;

import coding.exercise.oms.order.AggregatedOrder;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AggregatedOrderRepository {


    private Map<String, AggregatedOrder> map;

    public AggregatedOrderRepository() {
        this.map = new ConcurrentHashMap<>();
    }

    public AggregatedOrder findByType(String type) {
        return map.get(type);
    }




    public void addOrUpdateOrder(AggregatedOrder order) {
        map.put(order.getType(), order);
    }

    public Map<String, AggregatedOrder> retrieiveOrder() {
        return Collections.unmodifiableMap(map);
    }


}
