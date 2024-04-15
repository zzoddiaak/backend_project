package senla.repository.api;

import senla.entities.Role;

import java.util.List;

public interface RoleRepository {
    Role findById(Long uuid);
    List<Role> findAll();
    void save(Role role);
    void deleteById(Long uuid);
}
