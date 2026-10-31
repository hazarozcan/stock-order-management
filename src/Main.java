
public class Main {
    public static void main(String[] args) {

        Product product1 = new Product("Logitech-A345", "89579456", 23.5, 41.0, 10);
        Product product2 = new Product("Asus Tuf-k3", "25779456", 29.5, 51.0, 10);
        Product product3 = new Product("Hyperx Cloud-II", "87945686", 87.30, 110.21, 50);

        OrderItem orderItem = new OrderItem(product1, 1);
        OrderItem orderItem2 = new OrderItem(product1, 2);
        OrderItem orderItem3 = new OrderItem(product2, 6);
        OrderItem orderItem4 = new OrderItem(product3, 5); 
       
        Order order = new Order();

        order.addItem(orderItem);
        order.addItem(orderItem);
        order.addItem(orderItem);
        

        System.out.println(order.getStatus());
        System.out.println(product1.getStocklevel());
        order.confirmOrder();
        System.out.println(order.getStatus());
        System.out.println(product1.getStocklevel()); 
        System.out.println(order.getStatus());
        // order.confirmOrder();
        order.addItem(orderItem4);


       /*  order.addItem(orderItem2);
        order.addItem(orderItem4);
        order.addItem(orderItem3); */
    }

}