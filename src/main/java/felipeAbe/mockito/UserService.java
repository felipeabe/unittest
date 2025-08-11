package felipeAbe.mockito;

// Classe que depende do banco de dados para buscar status do usuário
public class UserService {
    private Database database;

    public UserService(Database database) {
        this.database = database;
    }

    public String getUserStatus(int id) {
        return database.getStatus(id);
    }
}