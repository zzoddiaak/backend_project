package senla.repository.api;

import senla.entities.Permission;
import senla.entities.Role;
import senla.enums.RoleName;

import java.util.List;

public interface RoleRepository {
    Role findById(Long uuid);
    List<Role> findAll();
    void save(Role role);
    void deleteById(Long uuid);
    public List<Role> findAllWithFetch(List<Permission> permission);
    public List<Role> findAllWithJoinFetch(RoleName roleName);

    public List<Role> findAllWithDetails();
}
