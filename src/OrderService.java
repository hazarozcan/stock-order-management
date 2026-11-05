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
    public void confirmOrder(int  orderId){
       
        Order order = orderRepository.getOrderById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order id not found!");
        }
        if (order.getStatus() != OrderStatus.DRAFT) {
            throw new IllegalArgumentException("Order status must be Draft");
            
        }


    }



}
