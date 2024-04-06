package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.Permission;
import senla.repository.AbstractRepository;
import senla.repository.iface.PermissionRepository;
@Repository

public class PermissionRepositoryImpl extends AbstractRepository<Permission> implements PermissionRepository {
    public PermissionRepositoryImpl(){
        save(Permission.builder()
                .permissionName("dsdsd")
                .build());
        save(Permission.builder()
                .permissionName("pdpsp")
                .build());
    }
}
