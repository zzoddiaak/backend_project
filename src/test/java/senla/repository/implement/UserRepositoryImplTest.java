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
import senla.entities.Role;
import senla.entities.User;
import senla.enums.RoleName;
import senla.repository.api.UserRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class UserRepositoryImplTest {
    @Resource
    private UserRepository userRepository;

    @Test
    public void findAllWithFetch() {
        User user = User.builder()
                .secondname("TEST")
                .build();

        userRepository.save(user);

        List<User> users = userRepository.findAllWithFetch(user.getSecondname());

        assertNotNull(users);
        assertFalse(users.isEmpty());

        users.forEach(x -> assertEquals(user.getSecondname(), x.getSecondname()));
    }

    @Test
    public void findAllWithJoinFetch() {
        User user = User.builder()
                .email("john@example.com")
                .build();

        userRepository.save(user);

        List<User> users = userRepository.findAllWithJoinFetch(user.getEmail());

        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void findAllWithDetails() {
        User user = User.builder()

                .role(Role.builder().id(1L).roleName(RoleName.ADMIN).build())
                .build();

        userRepository.save(user);

        List<User> users = userRepository.findAllWithDetails();

        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

}
