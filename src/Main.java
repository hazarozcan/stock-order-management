
public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Logitech-A345", "89579456", 23.5, 41.0, 10);

        System.out.println(product1.getProductstatus());
        product1.setProductstatus(ProductStatus.DISCONTINUED);
        System.out.println(product1.getProductstatus());

        try {
            product1.setProductstatus(ProductStatus.ACTIVE);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        Inventory inventory = new Inventory();
        Product product2 = new Product("Asus Tuf-k3", "25779456", 29.5, 51.0, 10);
        Product product3 = new Product("Logitech-A345", "89579456", 23.5, 41.0, 10);

        inventory.addProduct(product3);
        inventory.addProduct(product2);
        
        Product deneme1 = inventory.getProductByBarcode("25779456");
        System.out.println("Bulunan Ürün İsmi: " +deneme1.getProductname() + "\nBulunan ürün stok miktarı: " +deneme1.getStocklevel());
       
        Product deneme2 = inventory.getProductByBarcode("544564564");
        System.out.println("Bulunan Ürün İsmi: " +deneme2.getProductname() + "\nBulunan ürün stok miktarı: " +deneme2.getStocklevel());

    }

}