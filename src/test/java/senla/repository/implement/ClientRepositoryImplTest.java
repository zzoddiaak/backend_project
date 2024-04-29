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
import senla.entities.Client;
import senla.entities.Role;
import senla.entities.User;
import senla.enums.RoleName;
import senla.repository.api.ClientRepository;
import senla.repository.api.UserRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class ClientRepositoryImplTest{
    @Resource
    private ClientRepository clientRepository;



    @Test
    public void findAllWithDetails() {
        Client client = Client.builder()
                .build();

        clientRepository.save(client);

        List<Client> clients = clientRepository.findAllWithDetails();

        assertNotNull(clients);
        assertFalse(clients.isEmpty());
    }

}