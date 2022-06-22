package coding.exercise.oms.order;

public class AggregatedOrder {

    private int quantity;
    private String type;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AggregatedOrder{" +
                "quantity=" + quantity +
                ", type='" + type + '\'' +
                '}';
    }
}
