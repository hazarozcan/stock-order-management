
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderItem> orderitems = new ArrayList<>();
    
    private OrderStatus status;

    public Order(){
    this.status = OrderStatus.DRAFT;
    }

    public void addItem(OrderItem item) {

        if (item == null) {
            throw new IllegalArgumentException("Enter a valid value");
        }
        if (item.getProduct().getProductstatus() != ProductStatus.ACTIVE) {
            throw new IllegalArgumentException("Item status must be ACTİVE!");
        }
        if (item.getQuantity() > item.getProduct().getStocklevel()) {
            throw new IllegalArgumentException("There are not as many items in stock as indicated !");
        }

        int totalquantity = 0;

        for (OrderItem orders : orderitems) {

            if (item.getProduct().getBarcodenumber().equals(orders.getProduct().getBarcodenumber())) {

                totalquantity = totalquantity + (orders.getQuantity());
            }
        }

        totalquantity = totalquantity + item.getQuantity();

        if (totalquantity > item.getProduct().getStocklevel()) {
            throw new IllegalArgumentException("There are not as many items in stock as indicated");
        }
        orderitems.add(item);
    }

    public double calculateTotal() {

        double totalprice = 0.0;

        for (OrderItem o : orderitems) {
            double temp = (o.getProduct().getSaleprice() * o.getQuantity());
            totalprice = (totalprice + temp);
        }

        return totalprice;
    }

    public void confirmOrder() {

        for (OrderItem item : orderitems) {

            if (item.getProduct().getProductstatus() != ProductStatus.ACTIVE) {
                throw new IllegalArgumentException("Product do not available for sale!");
            }

            int totalquantity = 0;

            for (OrderItem orders : orderitems) {

                if (orders.getProduct().getBarcodenumber().equals(item.getProduct().getBarcodenumber())) {

                    totalquantity = totalquantity + orders.getQuantity();

                }

            }
            
            if (totalquantity > item.getProduct().getStocklevel()) {
                throw new IllegalArgumentException("There are not enough products!");
                
            }
        }

        for (OrderItem item : orderitems) {

            item.getProduct().decreaseStock(item.getQuantity());
            
        }

    }

}
