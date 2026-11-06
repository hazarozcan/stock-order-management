
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertSame;
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
        Order order = new Order(1001);

        assertEquals(1001, order.getOrderId());
        /* ordermanager.createOrder(); */

    }

    @Test
    void orderTotalShouldNotChangeWhenProductPriceChanges() {

        Order order = new Order(1001);
        Product product1 = new Product("Ryzen 9960x -4.20GHz", "9283701293", 82000, 95000, 5);

        order.addItem(new OrderItem(product1, 2));

        product1.setSaleprice(200);

        assertEquals(190000, order.calculateTotal());

    }

    @Test
    void cartShouldAllowMoreThanAvailableStock() {

        OrderRepository repository = new OrderRepository();
        Inventory inventory = new Inventory();
        OrderService service = new OrderService(repository, inventory);

        Product mouse = new Product("Mouse", "12345", 100, 150, 10);
        inventory.addProduct(mouse);

        Order order = service.createOrder();

        service.addProductToOrder(order.getOrderId(), "12345", 15);

        assertEquals(15, order.getItems().get(0).getQuantity());

        assertThrows(
                IllegalArgumentException.class,
                () -> service.confirmOrder(order.getOrderId()));

        assertEquals(10, mouse.getStocklevel());
        assertEquals(OrderStatus.DRAFT, order.getStatus());
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

        OrderRepository repository = new OrderRepository();
        Inventory inventory = new Inventory();
        OrderService service = new OrderService(repository, inventory);

        Product mouse = new Product("Mouse", "12345", 100, 150, 10);
        Product keyboard = new Product("Keyboard", "67890", 200, 300, 5);

        inventory.addProduct(mouse);
        inventory.addProduct(keyboard);

        Order order = service.createOrder();

        order.addItem(new OrderItem(mouse, 3));
        order.addItem(new OrderItem(keyboard, 4));

        keyboard.decreaseStock(3);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.confirmOrder(order.getOrderId()));

        assertEquals(10, mouse.getStocklevel());
        assertEquals(2, keyboard.getStocklevel());
        assertEquals(OrderStatus.DRAFT, order.getStatus());
    }

    @Test
    void orderShouldFollowCorrectStatusTransitions() {

        OrderRepository repository = new OrderRepository();
        Inventory inventory = new Inventory();
        OrderService service = new OrderService(repository, inventory);

        Product mouse = new Product("Mouse", "12345", 100, 150, 10);
        inventory.addProduct(mouse);

        Order order = service.createOrder();
        order.addItem(new OrderItem(mouse, 2));

        assertEquals(OrderStatus.DRAFT, order.getStatus());

        service.confirmOrder(order.getOrderId());
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());

        order.startPreparing();
        assertEquals(OrderStatus.PREPARING, order.getStatus());

        order.shipOrder();
        assertEquals(OrderStatus.SHIPPED, order.getStatus());

        order.deliverOrder();
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    void createOrderShouldSaveOrderInRepository() {

        OrderRepository repository = new OrderRepository();
        Inventory inventory = new Inventory();

        OrderService service = new OrderService(repository, inventory);

        Order order1 = service.createOrder();
        Order order2 = service.createOrder();

        assertEquals(1001, order1.getOrderId());
        assertEquals(1002, order2.getOrderId());

        assertEquals(2, repository.getAllOrders().size());

        assertEquals(order1, repository.getOrderById(1001));
        assertEquals(order2, repository.getOrderById(1002));
    }

    @Test
    void failedStockDecreaseShouldRollbackPreviousChanges() {

        OrderRepository repository = new OrderRepository();

        Inventory inventory = new Inventory() {

            private int callCount = 0;

            @Override
            public void decreaseStock(String barcode, int quantity) {

                callCount++;

                if (callCount == 2) {
                    throw new IllegalArgumentException("Simulated stock error");
                }

                super.decreaseStock(barcode, quantity);
            }
        };

        OrderService service = new OrderService(repository, inventory);

        Product mouse = new Product("Mouse", "12345", 100, 150, 10);
        Product keyboard = new Product("Keyboard", "67890", 200, 300, 5);

        inventory.addProduct(mouse);
        inventory.addProduct(keyboard);

        Order order = service.createOrder();

        order.addItem(new OrderItem(mouse, 3));
        order.addItem(new OrderItem(keyboard, 2));

        assertThrows(
                IllegalArgumentException.class,
                () -> service.confirmOrder(order.getOrderId()));

        assertEquals(10, mouse.getStocklevel());
        assertEquals(5, keyboard.getStocklevel());

        assertEquals(OrderStatus.DRAFT, order.getStatus());
    }

    @Test
    void inactiveProductShouldNotBeConfirmed() {

        OrderRepository repository = new OrderRepository();
        Inventory inventory = new Inventory();
        OrderService service = new OrderService(repository, inventory);

        Product mouse = new Product("Mouse", "12345", 100, 150, 10);
        inventory.addProduct(mouse);

        Order order = service.createOrder();
        order.addItem(new OrderItem(mouse, 3));

        mouse.setProductstatus(ProductStatus.INACTIVE);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.confirmOrder(order.getOrderId()));

        assertEquals(10, mouse.getStocklevel());
        assertEquals(OrderStatus.DRAFT, order.getStatus());
    }

    @Test
    void addProductToOrderShouldUseInventoryProduct() {

        OrderRepository repository = new OrderRepository();
        Inventory inventory = new Inventory();
        OrderService service = new OrderService(repository, inventory);

        Product mouse = new Product("Mouse", "12345", 100, 150, 10);
        inventory.addProduct(mouse);

        Order order = service.createOrder();

        service.addProductToOrder(order.getOrderId(), "12345", 3);

        assertEquals(1, order.getItems().size());
        assertEquals(3, order.getItems().get(0).getQuantity());

        assertSame(mouse, order.getItems().get(0).getProduct());
    }

    @Test
    void addProductToOrderShouldRejectUnknownProduct() {

        OrderRepository repository = new OrderRepository();
        Inventory inventory = new Inventory();
        OrderService service = new OrderService(repository, inventory);

        Order order = service.createOrder();

        assertThrows(
                IllegalArgumentException.class,
                () -> service.addProductToOrder(order.getOrderId(), "99999", 2));

        assertEquals(0, order.getItems().size());
    }
}
