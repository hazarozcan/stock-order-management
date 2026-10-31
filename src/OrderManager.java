import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order is empty!");
        }
        orders.add(order);
    }

}
