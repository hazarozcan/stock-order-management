import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {

        for (Order tempOrder : orders) {
            if (order.getOrderId() == tempOrder.getOrderId()) {
                throw new IllegalArgumentException("There cannot be multiple orders with the same ID.")   
            }
        }
        if (order == null) {
            throw new IllegalArgumentException("Order is empty!");
        }
        orders.add(order);
    }
    public List<Order> getAllOrders(){
      
        return new ArrayList<>(orders);
    }


}
