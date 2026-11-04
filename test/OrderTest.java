
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    void newOrderShouldStartAsDraft() {

        Order order = new Order(1001);
        assertEquals(OrderStatus.DRAFT, order.getStatus());

    }

    @Test
    void negativeOrderIdShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Order(-5));

    }

    @Test
    void ordersShouldHaveSequentialIds() {
        OrderManager ordermanager = new OrderManager();

        Order order1 = ordermanager.createOrder();

        assertEquals(1001, order1.getOrderId());
        /* ordermanager.createOrder(); */

    }

    @Test
    void orderTotalShouldNotChangeWhenProductPriceChanges() {

        OrderManager mng1 = new OrderManager();
        Product product1 = new Product("Ryzen 9960x -4.20GHz", "9283701293", 82000, 95000, 5);

        Order ordr1 = mng1.createOrder();
        ordr1.addItem(new OrderItem(product1, 2));

        product1.setSaleprice(200);

        assertEquals(190000, ordr1.calculateTotal());

    }

    @Test
    void insufficientStockShouldNotChangeCart() {

        Product mouse = new Product("Mouse", "12345", 100, 150, 10);

        OrderManager manager = new OrderManager();
        Order order = manager.createOrder();

        order.addItem(new OrderItem(mouse, 3));
        order.addItem(new OrderItem(mouse, 4));

        assertThrows(
                IllegalArgumentException.class,
                () -> order.updateItemQuantity("12345", 8));

        assertEquals(3, order.getItems().get(0).getQuantity());
        assertEquals(4, order.getItems().get(1).getQuantity());
    }

    @Test
    void decreaseStockTest() {

        Product mouse = new Product("Mouse", "12345", 100, 150, 10);

        assertThrows(
                IllegalArgumentException.class,
                () -> mouse.decreaseStock(15));

        assertEquals(10, mouse.getStocklevel());

    }

    @Test
    void confirmOrderShouldNotDecreaseStockWhenOneProductIsInsufficient() {

        Product mouse = new Product("Mouse", "12345", 100, 150, 10);
        Product keyboard = new Product("Keyboard", "67890", 200, 300, 5);

        OrderManager manager = new OrderManager();
        Order order = manager.createOrder();

        order.addItem(new OrderItem(mouse, 3));
        order.addItem(new OrderItem(keyboard, 4));

        keyboard.decreaseStock(3);

        assertThrows(
                IllegalArgumentException.class,
                () -> order.confirmOrder());

        assertEquals(10, mouse.getStocklevel());
        assertEquals(2, keyboard.getStocklevel());

        assertEquals(OrderStatus.DRAFT, order.getStatus());
    }

    @Test
    void orderShouldFollowCorrectStatusTransitions() {

        Product mouse = new Product("Mouse", "12345", 100, 150, 10);

        OrderManager manager = new OrderManager();
        Order order = manager.createOrder();

        order.addItem(new OrderItem(mouse, 2));

        assertEquals(OrderStatus.DRAFT, order.getStatus());

        order.confirmOrder();
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());

        order.startPreparing();
        assertEquals(OrderStatus.PREPARING, order.getStatus());

        order.shipOrder();
        assertEquals(OrderStatus.SHIPPED, order.getStatus());

        order.deliverOrder();
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }
}
