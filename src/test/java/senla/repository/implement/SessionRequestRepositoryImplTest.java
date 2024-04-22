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

import senla.repository.api.SessionRequestRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class SessionRequestRepositoryImplTest {
    @Resource
    private SessionRequestRepository requestRepository;

    @Test
    public void findAllWithFetch() {
        SessionRequest sessionRequest = SessionRequest.builder()
                .problem("TEST")
                .build();

        requestRepository.save(sessionRequest);

        List<SessionRequest> sessionRequests = requestRepository.findAllWithFetch(sessionRequest.getProblem());

        assertNotNull(sessionRequests);
        assertFalse(sessionRequests.isEmpty());

        sessionRequests.forEach(x -> assertEquals(sessionRequest.getProblem(), x.getProblem()));
    }

    @Test
    public void findAllWithJoinFetch() {
        SessionRequest sessionRequest = SessionRequest.builder()
                .requestDate(LocalDate.parse("2022-04-11"))
                .build();

        requestRepository.save(sessionRequest);

        List<SessionRequest> sessionRequests = requestRepository.findAllWithJoinFetch(sessionRequest.getRequestDate());

        assertNotNull(sessionRequests);
        assertFalse(sessionRequests.isEmpty());
    }


    @Test
    public void findAllWithDetails() {
        SessionRequest sessionRequest = SessionRequest.builder()

                .build();

        requestRepository.save(sessionRequest);

        List<SessionRequest> sessionRequests = requestRepository.findAllWithDetails();

        assertNotNull(sessionRequests);
        assertFalse(sessionRequests.isEmpty());
    }

}