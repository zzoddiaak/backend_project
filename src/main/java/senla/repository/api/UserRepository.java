package senla.repository.api;

import senla.entities.User;
import java.util.List;

public interface UserRepository {

    User findById(Long uuid);
    List<User> findAll();
    void save(User user);
    void deleteById(Long uuid);
    public List<User> findAllWithFetch(String secondname);
    public List<User> findAllWithJoinFetch(String email);
    public List<User> findAllWithDetails();
    public User findByEmail(String email);
}
