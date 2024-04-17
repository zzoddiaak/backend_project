package senla.repository.api;

import senla.entities.Permission;

import java.util.List;

public interface PermissionRepository {
    Permission findById(Long uuid);
    List<Permission> findAll();
    void save(Permission permission);
    void deleteById(Long uuid);
}
