package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.Role;
import senla.enums.RoleName;
import senla.repository.AbstractRepository;
import senla.repository.iface.RoleRepository;

@Repository
public class RoleRepositoryImpl extends AbstractRepository<Role> implements RoleRepository {

    public RoleRepositoryImpl() {
        save(Role.builder()
                .roleName(RoleName.PSYCHOLOGIST)
                .build());
        save(Role.builder()
                .roleName(RoleName.CLIENT)
                .build());
    }
}
