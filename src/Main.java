
public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Logitech-A345", "89579456", 23.5, 41.0, 10);
        // Product product2 = new Product("Asus Tuf-k3", "25779456", 29.5, 51.0, 10);

        Inventory inventory = new Inventory();

        inventory.addProduct(product1);

        inventory.discontinueProduct("89579456");

       

    }

}