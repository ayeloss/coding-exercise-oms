package coding.exercise.oms;

import coding.exercise.oms.repository.AggregatedOrderRepository;
import coding.exercise.oms.order.AggregatedOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AggregationRepositoryTest {

    private AggregatedOrderRepository repository = new AggregatedOrderRepository();


    @Test
    public void addOrUpdateOrder_success() {
        AggregatedOrder order = new AggregatedOrder();
        order.setType("fx");
        order.setQuantity(100);

        repository.addOrUpdateOrder(order);

        AggregatedOrder result = repository.findByType("fx");
        Assertions.assertEquals(order, result);
    }


}
