package senla.repository.api;

import senla.entities.Permission;
import senla.entities.Psychologist;
import senla.entities.Role;

import java.util.List;

public interface PermissionRepository {
    Permission findById(Long uuid);
    List<Permission> findAll();
    void save(Permission permission);
    void deleteById(Long uuid);
    public List<Permission> findAllWithJoinFetch(Role role);
    public List<Permission> findAllWithDetails();
}
