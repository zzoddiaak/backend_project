package senla.repository.implement;

import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.config.HibernateConfig;
import senla.config.LiquibaseConfig;
import senla.entities.Permission;
import senla.entities.Role;
import senla.enums.PermissionType;
import senla.enums.RoleName;
import senla.repository.api.PermissionRepository;
import senla.repository.api.RoleRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class PermissionRepositoryImplTest {
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private PermissionRepository permissionRepository;



    @Test
    public void findAllWithJoinFetch() {
        Role role = Role.builder()
                .roleName(RoleName.ADMIN)
                .build();

        roleRepository.save(role);

        List<Role> roles = roleRepository.findAllWithJoinFetch(role.getRoleName());

        assertNotNull(roles);
        assertFalse(roles.isEmpty());
    }


    @Test
    public void findAllWithDetails() {
        Role role = Role.builder()

                .build();

        roleRepository.save(role);

        List<Role> roles = roleRepository.findAllWithDetails();

        assertNotNull(roles);
        assertFalse(roles.isEmpty());
    }

}