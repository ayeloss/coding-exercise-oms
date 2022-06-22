package coding.exercise.oms.order;

public class Order {

    public Order(int quantity, String type, Direction direction) {
        this.quantity = quantity;
        this.type = type;
        this.direction = direction;
    }

    private int quantity;
    private String type;
    private Direction direction;

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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
