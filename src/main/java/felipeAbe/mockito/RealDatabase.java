package felipeAbe.mockito;

import java.util.HashMap;
import java.util.Map;

// Implementação real do banco de dados
public class RealDatabase implements Database {
    private Map<Integer, String> data = new HashMap<>();

    public RealDatabase() {
        data.put(1, "ACTIVE");
        data.put(2, "INACTIVE");
    }

    @Override
    public String getStatus(int id) {
        return data.getOrDefault(id, "UNKNOWN");
    }
}
