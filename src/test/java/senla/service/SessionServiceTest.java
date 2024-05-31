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
import senla.dto.session.SessionDTOToEntity;
import senla.dto.session.SessionShortDTO;
import senla.entities.Session;
import senla.mapper.SessionMapper;
import senla.repository.api.SessionRepository;
import senla.service.impl.SessionServiceImpl;


import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class SessionServiceTest{
    @InjectMocks
    private SessionServiceImpl sessionService;

    @Spy
    private SessionRepository sessionRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        Session session = Session.builder()
                .id(1L)
                .finalSession(LocalDateTime.parse("2022-04-11T12:30:00"))
                .build();

        Session session1 = Session.builder()
                .id(1L)
                .finalSession(LocalDateTime.parse("2028-04-11T12:30:00"))
                .build();

        Mockito.when(sessionRepository.findAll()).thenReturn(List.of(session, session1));

        List<SessionShortDTO> actual = sessionService.findAll();

        Mockito.verify(sessionRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        Session session = Session.builder()
                .id(1L)
                .finalSession(LocalDateTime.parse("2022-04-11T12:30:00"))
                .build();

        Mockito.when(sessionRepository.findById(any(Long.class))).thenReturn(session);

        SessionShortDTO expected = SessionMapper.convertToShortDto(session);
        SessionShortDTO actual = sessionService.findById(session.getId());

        Mockito.verify(sessionRepository, Mockito.times(1)).findById(any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getFinalSession(), actual.getFinalSession());
    }

    @Test
    public void saveTest(){
        SessionDTOToEntity dto = SessionDTOToEntity.builder()
                .finalSession(LocalDateTime.parse("2022-04-11T12:30:00"))
                .build();

        Session session = SessionMapper.createSessionDto(dto);

        Mockito.doNothing().when(sessionRepository).save(any(Session.class));

        sessionService. save(dto);

        Mockito.verify(sessionRepository, Mockito.times(1)).save(any(Session.class));
    }

    @Test
    public void updateTest(){
        Session session = Session.builder()
                .id(1L)
                .finalSession(LocalDateTime.parse("2022-04-11T12:30:00"))
                .build();

        SessionDTOToEntity dto = new SessionDTOToEntity();
        dto.setFinalSession(LocalDateTime.parse("2022-04-11T12:30:00"));

        Mockito.when(sessionRepository.findById(session.getId())).thenReturn(session);

        sessionService.update(session.getId(), dto);

        Mockito.verify(sessionRepository, Mockito.times(1)).save(any(Session.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        sessionService.deleteById(id);
        Mockito.verify(sessionRepository, Mockito.times(1)).deleteById(id);
    }
}
