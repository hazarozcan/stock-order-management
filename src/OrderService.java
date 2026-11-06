import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.basic.BasicDirectoryModel;

public class OrderService {

    private final OrderRepository orderRepository;
    private final Inventory inventory;

    public OrderService(OrderRepository orderRepository, Inventory inventory) {

        if (orderRepository == null || inventory == null) {
            throw new IllegalArgumentException("Enter a valid value");

        }
        this.orderRepository = orderRepository;
        this.inventory = inventory;

    }

    public Order createOrder() {

        Order order = new Order(orderRepository.generateOrderId());
        orderRepository.addOrder(order);
        return order;
    }

    public void confirmOrder(int orderId) {

        Order order = orderRepository.getOrderById(orderId);
        
        if (order == null) {
            throw new IllegalArgumentException("Order id not found!");
        }
        if (order.getStatus() != OrderStatus.DRAFT) {
            throw new IllegalArgumentException("Order status must be Draft");
        
        }

        Map<String, Integer> requiredQuantities = new HashMap<>();

        for (OrderItem item : order.getItems()) {
                      
            String barcode = item.getProduct().getBarcodenumber();
            int quantity = item.getQuantity();

            int total = requiredQuantities.getOrDefault(barcode, 0);
            
            total = total + quantity;

            requiredQuantities.put(barcode, total);                     
        }

        for (Map.Entry<String,Integer> entry : requiredQuantities.entrySet()) {
            
            if (inventory.hasEnoughStock(entry.getKey(), entry.getValue()) == false) {
                throw new IllegalArgumentException("Stock not enough!");
                
            }
            
        }

    }

}
