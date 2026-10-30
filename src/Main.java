import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Logitech-A345", "89579456", 23.5, 41.0, 10);
        Product product2 = new Product("Asus Tuf-k3", "25779456", 29.5, 51.0, 10);

        Inventory inventory = new Inventory();

        inventory.addProduct(product1);
        inventory.addProduct(product2);

        Collection<Product> s = inventory.getAllProducts();

       for (Product ghost : s) {
        System.out.println("Ürün : " +ghost.getProductname() + " Barkod: " + ghost.getBarcodenumber() + " Stok: " + ghost.getStocklevel());
        
       }

    }

}