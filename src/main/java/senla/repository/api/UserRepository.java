package senla.repository.api;

import senla.entities.User;

import java.util.List;

public interface UserRepository {
    User findById(Long uuid);
    List<User> findAll();
    void save(User user);
    void deleteById(Long uuid);
}
