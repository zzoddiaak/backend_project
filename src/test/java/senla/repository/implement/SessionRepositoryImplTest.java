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
import senla.repository.api.SessionRepository;
import senla.repository.api.SessionRequestRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class SessionRepositoryImplTest {
    @Resource
    private SessionRepository sessionRepository;

    @Test
    public void findAllWithFetch() {
        Session session = Session.builder()
                .startSession(LocalDateTime.parse("2022-04-11T12:30:00"))
                .build();

        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findAllWithFetch(session.getStartSession());

        assertNotNull(sessions);
        assertFalse(sessions.isEmpty());

        sessions.forEach(x -> assertEquals(session.getStartSession(), x.getStartSession()));
    }

    @Test
    public void findAllWithJoinFetch() {
        Session session = Session.builder()
                .finalSession(LocalDateTime.parse("2022-04-11T12:30:00"))
                .build();

        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findAllWithJoinFetch(session.getFinalSession());

        assertNotNull(sessions);
        assertFalse(sessions.isEmpty());
    }


    @Test
    public void findAllWithDetails() {
        Session session = Session.builder()

                .build();

        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findAllWithDetails();

        assertNotNull(sessions);
        assertFalse(sessions.isEmpty());
    }

}