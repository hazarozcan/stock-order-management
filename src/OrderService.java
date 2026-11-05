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

        OrderRepository repositorycall = new OrderRepository();   
        Order order = new Order(repositorycall.generateOrderId());
        return order;
    }



}
