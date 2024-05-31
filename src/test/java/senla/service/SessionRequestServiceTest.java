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
import senla.dto.sessionrequest.SessionRequestDTO;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;
import senla.entities.SessionRequest;
import senla.mapper.SessionRequestMapper;
import senla.repository.api.SessionRequestRepository;
import senla.service.impl.SessionRequestServiceImpl;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class SessionRequestServiceTest{
    @InjectMocks
    private SessionRequestServiceImpl sessionRequestService;

    @Spy
    private SessionRequestRepository sessionRequestRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        SessionRequest sessionRequest = SessionRequest.builder()
                .id(1L)
                .problem("test")
                .build();

        SessionRequest sessionRequest1 = SessionRequest.builder()
                .id(1L)
                .problem("TEST")
                .build();

        Mockito.when(sessionRequestRepository.findAll()).thenReturn(List.of(sessionRequest, sessionRequest1));

        List<SessionRequestDTO> actual = sessionRequestService.findAll();

        Mockito.verify(sessionRequestRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        SessionRequest sessionRequest = SessionRequest.builder()
                .id(1L)
                .problem("teest")
                .build();

        Mockito.when(sessionRequestRepository.findById(any(Long.class))).thenReturn(sessionRequest);

        SessionRequestDTO expected = SessionRequestMapper.convertToShortDto(sessionRequest);
        SessionRequestDTO actual = sessionRequestService.findById(sessionRequest.getId());

        Mockito.verify(sessionRequestRepository, Mockito.times(1)).findById(any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getProblem(), actual.getProblem());
    }

    @Test
    public void saveTest(){
        SessionRequestDTOToEntity dto = SessionRequestDTOToEntity.builder()
                .problem("test")
                .build();

        SessionRequest sessionRequest = SessionRequestMapper.createSessionRequestDto(dto);

        Mockito.doNothing().when(sessionRequestRepository).save(any(SessionRequest.class));

        sessionRequestService. save(dto);

        Mockito.verify(sessionRequestRepository, Mockito.times(1)).save(any(SessionRequest.class));
    }

    @Test
    public void updateTest(){
        SessionRequest sessionRequest = SessionRequest.builder()
                .id(1L)
                .problem("test")

                .build();

        SessionRequestDTOToEntity dto = new SessionRequestDTOToEntity();
        dto.setProblem("testt");

        Mockito.when(sessionRequestRepository.findById(sessionRequest.getId())).thenReturn(sessionRequest);

        sessionRequestService.update(sessionRequest.getId(), dto);

        Mockito.verify(sessionRequestRepository, Mockito.times(1)).save(any(SessionRequest.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        sessionRequestService.deleteById(id);
        Mockito.verify(sessionRequestRepository, Mockito.times(1)).deleteById(id);
    }
}
