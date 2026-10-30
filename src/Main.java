
public class Main {
    public static void main(String[] args) {
        Product product = new Product("Logitech-A345", "89579456", 23.5, 41.0,10);

        System.out.println(product.getProductstatus());
        product.setProductstatus(ProductStatus.DISCONTINUED);
        System.out.println(product.getProductstatus());
        
        try{
            product.setProductstatus(ProductStatus.ACTIVE);


        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

}