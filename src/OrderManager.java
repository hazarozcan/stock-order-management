import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrdersByStatus(OrderStatus status) {

        ArrayList<Order> filtre = new ArrayList<>();

        if (status == null) {
            throw new IllegalArgumentException("Enter a valid value!");
        }

        for (Order temporder : orders) {
            if (temporder.getStatus() == status) {
                filtre.add(temporder);
            }
        }
         return filtre;
    }

    public void addOrder(Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Order is empty!");
        }

        for (Order tempOrder : orders) {
            if (order.getOrderId() == tempOrder.getOrderId()) {
                throw new IllegalArgumentException("There cannot be multiple orders with the same ID.");
            }
        }

        orders.add(order);
    }

    public List<Order> getAllOrders() {

        return new ArrayList<>(orders);
    }

    public Order getOrderById(int orderId) {

        for (Order id : orders) {
            if (id.getOrderId() == orderId) {
                return id;
            }
        }
        return null;
    }

}
