package senla.repository.implement;

import jakarta.annotation.Resource;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.config.HibernateConfig;
import senla.config.LiquibaseConfig;
import senla.entities.ClientCard;
import senla.entities.Role;
import senla.entities.User;
import senla.enums.RoleName;
import senla.repository.api.ClientCardRepository;
import senla.repository.api.UserRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class ClientCardRepositoryImplTest {

    @Resource
    private ClientCardRepository clientCardRepository;

    @Test
    public void findAllWithFetch() {
        ClientCard clientCard = ClientCard.builder()
                .recommendations("TEST")
                .build();

        clientCardRepository.save(clientCard);

        List<ClientCard> clientCards = clientCardRepository.findAllWithFetch(clientCard.getRecommendations());

        assertNotNull(clientCards);
        assertFalse(clientCards.isEmpty());

        clientCards.forEach(x -> assertEquals(clientCard.getRecommendations(), x.getRecommendations()));
    }

    @Test
    public void findAllWithJoinFetch() {
        ClientCard clientCard = ClientCard.builder()
                .diagnos("Test")
                .build();

        clientCardRepository.save(clientCard);

        List<ClientCard> clientCards = clientCardRepository.findAllWithJoinFetch(clientCard.getDiagnos());

        assertNotNull(clientCards);
        assertFalse(clientCards.isEmpty());
    }

    @Test
    public void findAllWithDetails() {
        ClientCard clientCard = ClientCard.builder()
                .build();

        clientCardRepository.save(clientCard);

        List<ClientCard> clientCards = clientCardRepository.findAllWithDetails();

        assertNotNull(clientCards);
        assertFalse(clientCards.isEmpty());
    }
}