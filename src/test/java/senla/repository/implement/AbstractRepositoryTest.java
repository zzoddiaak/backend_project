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
import senla.entities.User;
import senla.repository.api.UserRepository;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class AbstractRepositoryTest {
    @Resource
    private UserRepository userRepository;

    @Test
    public void SaveTest(){
        User user = User.builder()
                .firstname("TEST")
                .secondname("ACCOUNT")
                .build();

        userRepository.save(user);

        User testUser = userRepository.findById(user.getId());

        assertNotNull(testUser);
        assertEquals(user.getFirstname(), testUser.getFirstname());
    }


    @Test
    public void FindAllTest(){
        User user = User.builder()
                .firstname("TEST")
                .secondname("ACCOUNT")
                .build();

        userRepository.save(user);

        List<User> testUser = userRepository.findAll();

        assertNotNull(testUser);
        assertFalse(testUser.isEmpty());
    }

    @Test
    public void DeleteTest(){
        User user = User.builder()
                .firstname("TEST")
                .secondname("ACCOUNT")
                .userPassword("test")
                .email("tEst")
                .build();

        userRepository.save(user);

        User testUser = userRepository.findById(user.getId());

        assertNotNull(testUser);
        assertEquals(user.getFirstname(), testUser.getFirstname());

        userRepository.deleteById(user.getId());

        testUser = userRepository.findById(user.getId());

        assertNull(testUser);
    }

    @Test
    public void UpdateTest(){
        User user = User.builder()
                .firstname("TEST")
                .secondname("ACCOUNT")
                .build();

        userRepository.save(user);

        String updatedFirstname = "UPDATE";

        user.setFirstname(updatedFirstname);
        userRepository.save(user);

        User testUser = userRepository.findById(user.getId());

        assertNotNull(testUser);
        assertEquals(testUser.getFirstname(), updatedFirstname);
        assertEquals(testUser.getSecondname(), user.getSecondname());
    }
}