package senla.repository.iface;

import senla.entities.Client;
import senla.entities.Role;

import java.util.List;

public interface RoleRepository {
    Role findById(Long uuid);
    List<Role> findAll();
    void save(Role role);
    void deleteById(Long uuid);
}
