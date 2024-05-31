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
import senla.dto.user.UserDTOToEntity;
import senla.dto.user.UserShortInfoDTO;
import senla.entities.User;
import senla.mapper.UserMapper;
import senla.repository.api.UserRepository;
import senla.service.impl.UserServiceImpl;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class UserServiceTest{
    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    private UserRepository userRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        User user = User.builder()
                .id(1L)
                .email("test@senla.com")
                .build();

        User user1 = User.builder()
                .id(1L)
                .email("teest@senla.com")
                .build();

        Mockito.when(userRepository.findAll()).thenReturn(List.of(user, user1));

        List<UserShortInfoDTO> actual = userService.findAll();

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        User user = User.builder()
                .id(1L)
                .email("test")
                .build();

        Mockito.when(userRepository.findById(any(Long.class))).thenReturn(user);

        UserShortInfoDTO expected = UserMapper.convertToShortDto(user);
        UserShortInfoDTO actual = userService.findById(user.getId());

        Mockito.verify(userRepository, Mockito.times(1)).findById(any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getEmail(), actual.getEmail());
    }

    @Test
    public void saveTest(){
        UserDTOToEntity dto = UserDTOToEntity.builder()
                .email("test")
                .build();

        User user = UserMapper.createUserDto(dto);

        Mockito.doNothing().when(userRepository).save(any(User.class));

        userService. save(dto);

        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    @Test
    public void updateTest(){
        User user = User.builder()
                .id(1L)
                .email("tesr")
                .build();

        UserDTOToEntity dto = new UserDTOToEntity();
        dto.setEmail("test");

        Mockito.when(userRepository.findById(user.getId())).thenReturn(user);

        userService.update(user.getId(), dto);

        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        userService.deleteById(id);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(id);
    }
}
