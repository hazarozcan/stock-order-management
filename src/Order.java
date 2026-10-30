
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderItem> orderitems = new ArrayList<>();

    public void addItem(OrderItem item) {

        if (item == null) {
            throw new IllegalArgumentException("Enter a valid value");
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

}
