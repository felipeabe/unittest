package felipeAbe.authms;

// Repositório de usuários
public interface UserRepository {
    void save(User user);
    User findByUsername(String username);
}
