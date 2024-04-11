package senla.repository.iface;

import senla.entities.Client;
import senla.entities.User;

import java.util.List;

public interface UserRepository {
    User findById(Long uuid);
    List<User> findAll();
    void save(User user);
    void deleteById(Long uuid);
}
