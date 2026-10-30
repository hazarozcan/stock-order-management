import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;

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

    public Collection<Product> getAllProducts() {

        return new ArrayList<>(productCatalog.values());
    }

    public void dicontinueProduct(String barcode) {

        if (getProductByBarcode(barcode) == null) {
            throw new IllegalArgumentException(barcode);
        }
        else{
            getProductByBarcode(barcode).setProductstatus(ProductStatus.DISCONTINUED);
        }

    }
}
