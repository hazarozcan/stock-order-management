
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderItem> orderitems = new ArrayList<>();
    private OrderStatus status;
    private final int orderId;

    Order(int orderId) {

        if (orderId <= 0) {
            throw new IllegalArgumentException();
        }
        this.orderId = orderId;
        this.status = OrderStatus.DRAFT;

    }

    public void addItem(OrderItem item) {

        if (status != OrderStatus.DRAFT) {
            throw new IllegalArgumentException("Item status not be Draft");
        }
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
            double temp = (o.getUnitPrice() * o.getQuantity());
            totalprice = (totalprice + temp);
        }

        return totalprice;
    }

    public void confirmOrder() {

        if (orderitems.isEmpty()) {

            throw new IllegalArgumentException("Cannot confirm an empty order");

        }

        if (status != OrderStatus.DRAFT) {
            throw new IllegalArgumentException("Item status must be Draft");
        }

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
        status = OrderStatus.CONFIRMED;
    }

    public void cancelOrder() {

        if (status != OrderStatus.DRAFT) {

            throw new IllegalArgumentException("This operation cannot be performed.");
        }

        status = OrderStatus.CANCELLED;
    }

    public OrderStatus getStatus() {

        return status;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<OrderItem> getItems() {

        return new ArrayList<>(orderitems);
    }

    public void removeItem(String barcode) {

        if (barcode == null || barcode.isEmpty()) {
            throw new IllegalArgumentException();

        }
        if (status != OrderStatus.DRAFT) {
            throw new IllegalArgumentException();
        }

        for (OrderItem orderItem : orderitems) {
            if (orderItem.getProduct().getBarcodenumber().equals(barcode)) {
                orderitems.remove(orderItem);
                return;
            }
        }
        throw new IllegalArgumentException();

    }

    public void updateItemQuantity(String barcode, int newQuantity) {

        if (status != OrderStatus.DRAFT) {

            throw new IllegalArgumentException("Product status must be \"Draft\" proccessing");
        }

        if (barcode == null || barcode.equals("") || newQuantity <= 0) {

            throw new IllegalArgumentException("enter a valid value");

        }

        for (int i = 0; i < orderitems.size(); i++) {

            OrderItem tempitem = orderitems.get(i);

            if (tempitem.getProduct().getBarcodenumber().equals(barcode)) {

                OrderItem newItem = new OrderItem(tempitem.getProduct(), newQuantity);

                int totalQuantity = newQuantity;

                for (OrderItem tempitems : orderitems) {

                    if (barcode.equals(tempitems.getProduct().getBarcodenumber())) {

                        totalQuantity = totalQuantity + tempitems.getQuantity();

                        if (totalQuantity + newItem.getQuantity() > tempitems.getProduct().getStocklevel()) {

                            throw new IllegalArgumentException("The specified quantity is not in stock");
                        }

                    }

                }

                orderitems.set(i, newItem);
                return;
            }
        }

        throw new IllegalArgumentException("Product not found!");

    }
}
