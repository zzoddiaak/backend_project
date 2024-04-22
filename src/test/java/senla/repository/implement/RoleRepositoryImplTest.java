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
import senla.entities.*;
import senla.enums.PermissionType;
import senla.enums.RoleName;
import senla.repository.api.PermissionRepository;
import senla.repository.api.RoleRepository;
import senla.repository.api.SessionRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class RoleRepositoryImplTest {
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private PermissionRepository permissionRepository;

    @Test
    public void findAllWithFetch() {
        Permission permission = Permission.builder()
                .permissionType(PermissionType.DELETE)
                .build();
        permissionRepository.save(permission);

        Role role = Role.builder()
                .permission(Collections.singletonList(permission))
                .build();
        roleRepository.save(role);

        List<Role> roles = roleRepository.findAllWithFetch(Collections.singletonList(permission));

        assertNotNull(roles);
        assertFalse(roles.isEmpty());

        roles.forEach(roleItem -> assertTrue(roleItem.getPermission().contains(permission)));
    }


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