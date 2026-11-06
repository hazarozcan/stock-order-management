
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderItem> orderitems = new ArrayList<>();
    private OrderStatus status;
    private final int orderId;

    Order(int orderId) {

        if (orderId <= 0) {
            throw new IllegalArgumentException();
        }
        this.orderId = orderId;
        this.status = OrderStatus.DRAFT;

    }

    void addItem(OrderItem item) {

        if (status != OrderStatus.DRAFT) {
            throw new IllegalArgumentException("Item status not be Draft");
        }
        if (item == null) {
            throw new IllegalArgumentException("Enter a valid value");
        }
        if (item.getProduct().getProductstatus() != ProductStatus.ACTIVE) {
            throw new IllegalArgumentException("Item status must be ACTİVE!");
        }
        orderitems.add(item);
    }

    public double calculateTotal() {

        double totalprice = 0.0;

        for (OrderItem o : orderitems) {
            double temp = (o.getUnitPrice() * o.getQuantity());
            totalprice = (totalprice + temp);
        }

        return totalprice;
    }

    public void cancelOrder() {

        if (status != OrderStatus.DRAFT) {

            throw new IllegalArgumentException("This operation cannot be performed.");
        }

        status = OrderStatus.CANCELLED;
    }

    public OrderStatus getStatus() {

        return status;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<OrderItem> getItems() {

        return new ArrayList<>(orderitems);
    }

    public void removeItem(String barcode) {

        if (barcode == null || barcode.isEmpty()) {
            throw new IllegalArgumentException();

        }
        if (status != OrderStatus.DRAFT) {
            throw new IllegalArgumentException();
        }

        for (OrderItem orderItem : orderitems) {
            if (orderItem.getProduct().getBarcodenumber().equals(barcode)) {
                orderitems.remove(orderItem);
                return;
            }
        }
        throw new IllegalArgumentException();

    }

    public void updateItemQuantity(String barcode, int newQuantity) {

        if (status != OrderStatus.DRAFT) {
            throw new IllegalArgumentException("Order must be DRAFT!");
        }

        if (barcode == null || barcode.isEmpty() || newQuantity <= 0) {
            throw new IllegalArgumentException("Enter a valid value!");
        }

        for (int i = 0; i < orderitems.size(); i++) {

            OrderItem tempItem = orderitems.get(i);

            if (tempItem.getProduct().getBarcodenumber().equals(barcode)) {

                OrderItem newItem = new OrderItem(tempItem.getProduct(), newQuantity, tempItem.getUnitPrice());

                orderitems.set(i, newItem);
                return;
            }
        }

        throw new IllegalArgumentException("Product not found!");
    }

    public void startPreparing() {
        if (status != OrderStatus.CONFIRMED) {
            throw new IllegalArgumentException("Order status must be confirmed");

        }
        status = OrderStatus.PREPARING;

    }

    public void shipOrder() {
        if (status != OrderStatus.PREPARING) {
            throw new IllegalArgumentException("Order status must be preparing");
        }
        status = OrderStatus.SHIPPED;

    }

    public void deliverOrder() {
        if (status != OrderStatus.SHIPPED) {
            throw new IllegalArgumentException("Order status must be shipped");

        }
        status = OrderStatus.DELIVERED;
    }

    void markAsConfirmed() {
        if (status != OrderStatus.DRAFT || orderitems.isEmpty()) {

            throw new IllegalArgumentException("Enter a valid value");
        }
        status = OrderStatus.CONFIRMED;

    }
}
