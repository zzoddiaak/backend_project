package senla.servise;

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
import senla.dto.permission.PermissionDTO;
import senla.dto.permission.PermissionDTOToEntity;
import senla.entities.Permission;
import senla.enums.PermissionType;
import senla.mapper.PermissionMapper;
import senla.repository.api.PermissionRepository;
import senla.servise.implement.PermissionServiceImpl;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class PermissionServiceTest{
    @InjectMocks
    private PermissionServiceImpl permissionService;

    @Spy
    private PermissionRepository permissionRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        Permission permission = Permission.builder()
                .id(1L)
                .permissionType(PermissionType.DELETE)
                .build();

        Permission permission1 = Permission.builder()
                .id(1L)
                .permissionType(PermissionType.ADMIN)
                .build();

        Mockito.when(permissionRepository.findAll()).thenReturn(List.of(permission, permission1));

        List<PermissionDTO> actual = permissionService.findAll();

        Mockito.verify(permissionRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        Permission permission = Permission.builder()
                .id(1L)
                .permissionType(PermissionType.ADMIN)

                .build();

        Mockito.when(permissionRepository.findById(any(Long.class))).thenReturn(permission);

        PermissionDTO expected = PermissionMapper.convertToDto(permission);
        PermissionDTO actual = permissionService.findById(permission.getId());

        Mockito.verify(permissionRepository, Mockito.times(1)).findById(Mockito.any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getPermissionType(), actual.getPermissionType());
    }

    @Test
    public void saveTest(){
        PermissionDTOToEntity dto = PermissionDTOToEntity.builder()
                .permissionType(PermissionType.ADMIN)
                .build();

        Permission permission = PermissionMapper.createPermissionDto(dto);

        Mockito.doNothing().when(permissionRepository).save(Mockito.any(Permission.class));

        permissionService.save(dto);

        Mockito.verify(permissionRepository, Mockito.times(1)).save(Mockito.any(Permission.class));
    }

    @Test
    public void updateTest(){
        Permission permission = Permission.builder()
                .id(1L)
                .permissionType(PermissionType.ADMIN)
                .build();

        PermissionDTOToEntity dto = new PermissionDTOToEntity();
        dto.setPermissionType(PermissionType.DELETE);

        Mockito.when(permissionRepository.findById(permission.getId())).thenReturn(permission);

        permissionService.update(permission.getId(), dto);

        Mockito.verify(permissionRepository, Mockito.times(1)).save(Mockito.any(Permission.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        permissionService.deleteById(id);
        Mockito.verify(permissionRepository, Mockito.times(1)).deleteById(id);
    }
}
