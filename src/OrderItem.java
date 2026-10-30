public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {

        if (product == null || quantity <= 0) {

            throw new IllegalArgumentException("Enter a valid value");
        } else {
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

}
