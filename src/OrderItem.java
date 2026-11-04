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

    public OrderItem(Product product, int quantity, double unitPrice) {

        if (product == null || quantity <= 0 || unitPrice <= 0) {
            throw new IllegalArgumentException("Enter a valid value!");
        }

        this.product = product;
        this.quantity = quantity;
        this.unitprice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitprice;
    }
}
