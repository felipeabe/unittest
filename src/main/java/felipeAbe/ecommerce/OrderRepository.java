package felipeAbe.ecommerce;

import java.util.HashMap;
import java.util.Map;

public interface OrderRepository {
    void save(Order order);
    Order findById(int id);
}

