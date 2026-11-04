public class OrderItem {
   
    private final Product product;
    private final int quantity;
    private final double unitprice;

    public OrderItem(Product product, int quantity) {

        if (product == null || quantity <= 0) {

            throw new IllegalArgumentException("Enter a valid value");
        } else {
            this.unitprice = product.getSaleprice();
            this.product = product;
            this.quantity = quantity;
        }
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice(){
        return unitprice;
    }
}

