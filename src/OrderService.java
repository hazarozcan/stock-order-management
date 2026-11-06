
import java.util.HashMap;
import java.util.Map;

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
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException("list not to be empty");

        }

        Map<String, Integer> requiredQuantities = new HashMap<>();

        for (OrderItem item : order.getItems()) {
            if (item.getProduct().getProductstatus() != ProductStatus.ACTIVE) {
                throw new IllegalArgumentException("product status must be Active!");

            }

            String barcode = item.getProduct().getBarcodenumber();
            int quantity = item.getQuantity();

            int total = requiredQuantities.getOrDefault(barcode, 0);

            total = total + quantity;

            requiredQuantities.put(barcode, total);
        }

        for (Map.Entry<String, Integer> entry : requiredQuantities.entrySet()) {

            if (inventory.hasEnoughStock(entry.getKey(), entry.getValue()) == false) {
                throw new IllegalArgumentException("Stock not enough!");

            }

        }

        Map<String, Integer> decreasedQuantities = new HashMap<>();

        try {
            for (Map.Entry<String, Integer> entry : requiredQuantities.entrySet()) {
                inventory.decreaseStock(entry.getKey(), entry.getValue());
                decreasedQuantities.put(entry.getKey(), entry.getValue());

            }
            order.markAsConfirmed();

        } catch (RuntimeException e) {

            for (Map.Entry<String, Integer> entry : decreasedQuantities.entrySet()) {
                inventory.increaseStock(entry.getKey(), entry.getValue());

            }
            throw e;
        }

    }

    public void addProductToOrder(int orderId, String barcode, int quantitiy) {

        if (orderRepository.getOrderById(orderId) == null) {
            throw new IllegalArgumentException("Order not found!");
        }
        if (inventory.getProductByBarcode(barcode) == null) {
            throw new IllegalArgumentException("Product not found");
            
        }
        Order order = orderRepository.getOrderById(orderId);
        Product product = inventory.getProductByBarcode(barcode);
        OrderItem orderitem = new OrderItem(product, quantitiy);
        order.addItem(orderitem);
       
    }
}
