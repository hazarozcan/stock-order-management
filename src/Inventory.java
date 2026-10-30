import java.util.HashMap;

public class Inventory {

    private HashMap<String, Product> productCatalog = new HashMap<>();

    public void addProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Enter a valid value!");
        } else if (productCatalog.containsKey(product.getBarcodenumber())) {
            throw new IllegalArgumentException("The product you are trying to add is already registered in the system");
        } else {
            productCatalog.put(product.getBarcodenumber(), product);
        }

    }

    public Product getProductByBarcode(String barcode) {
             
        return productCatalog.get(barcode);

    }

}
