
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
    void ordersShouldHaveSequentialIds(){
        OrderManager ordermanager = new OrderManager();

        Order order1 = ordermanager.createOrder();


        assertEquals(1001, order1.getOrderId());
        /* ordermanager.createOrder(); */

    }

}
