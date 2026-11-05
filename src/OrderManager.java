import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private List<Order> orders = new ArrayList<>();
    private final Inventory inventory;


    public OrderManager(Inventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("Enter a valid value");
        }
        this.inventory = inventory;

    }

    

    
    

   

    
}
