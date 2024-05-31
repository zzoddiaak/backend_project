package senla.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.config.HibernateConfig;
import senla.config.LiquibaseConfig;
import senla.dto.role.RoleDTO;
import senla.dto.role.RoleDTOToEntity;
import senla.entities.Role;
import senla.enums.RoleName;
import senla.mapper.RoleMapper;
import senla.repository.api.RoleRepository;
import senla.service.impl.RoleServiceImpl;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class RoleServiceTest{
    @InjectMocks
    private RoleServiceImpl roleService;

    @Spy
    private RoleRepository roleRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        Role role = Role.builder()
                .id(1L)
                .roleName(RoleName.ADMIN)
                .build();

        Role role1 = Role.builder()
                .id(1L)
                .roleName(RoleName.CLIENT)
                .build();

        Mockito.when(roleRepository.findAll()).thenReturn(List.of(role, role1));

        List<RoleDTO> actual = roleService.findAll();

        Mockito.verify(roleRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        Role role = Role.builder()
                .id(1L)
                .roleName(RoleName.ADMIN)
                .build();

        Mockito.when(roleRepository.findById(any(Long.class))).thenReturn(role);

        RoleDTO expected = RoleMapper.convertToDto(role);
        RoleDTO actual = roleService.findById(role.getId());

        Mockito.verify(roleRepository, Mockito.times(1)).findById(any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getRoleName(), actual.getRoleName());
    }

    @Test
    public void saveTest(){
        RoleDTOToEntity dto = RoleDTOToEntity.builder()
                .roleName(RoleName.ADMIN)
                .build();

        Role role = RoleMapper.createRoleDto(dto);

        Mockito.doNothing().when(roleRepository).save(any(Role.class));

        roleService. save(dto);

        Mockito.verify(roleRepository, Mockito.times(1)).save(any(Role.class));
    }

    @Test
    public void updateTest(){
        Role psychologist = Role.builder()
                .id(1L)
                .roleName(RoleName.ADMIN)

                .build();

        RoleDTOToEntity dto = new RoleDTOToEntity();
        dto.setRoleName(RoleName.ADMIN);

        Mockito.when(roleRepository.findById(psychologist.getId())).thenReturn(psychologist);

        roleService.update(psychologist.getId(), dto);

        Mockito.verify(roleRepository, Mockito.times(1)).save(any(Role.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        roleService.deleteById(id);
        Mockito.verify(roleRepository, Mockito.times(1)).deleteById(id);
    }
}
