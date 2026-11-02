
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
        Product product1 = new Product("Ryzen 9960x", "9283701293", 32000, 54000, 5);

        Order ordr1 = mng1.createOrder();
        ordr1.addItem(new OrderItem(product1, 2));

        product1.setSaleprice(200);

        assertEquals(108000,ordr1.calculateTotal());



    }

}
