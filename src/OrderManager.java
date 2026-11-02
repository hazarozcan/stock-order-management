import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private List<Order> orders = new ArrayList<>();
    
    private int nextOrderId = 1001;

    public Order createOrder(){
        
        Order order = new Order(nextOrderId);
        addOrder(order);
        this.nextOrderId++;
        return order;
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {

        ArrayList<Order> filteredOrders = new ArrayList<>();

        if (status == null) {
            throw new IllegalArgumentException("Enter a valid value!");
        }

        for (Order temporder : orders) {
            if (temporder.getStatus() == status) {
                filteredOrders.add(temporder);
            }
        }
         return filteredOrders;
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
