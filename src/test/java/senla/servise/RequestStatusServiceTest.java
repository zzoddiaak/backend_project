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
import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.requeststatus.RequestStatusDTOToEntity;
import senla.entities.RequestStatus;
import senla.enums.StatusRequest;
import senla.mapper.RequestStatusMapper;
import senla.repository.api.RequestStatusRepository;
import senla.servise.implement.RequestStatusServiceImpl;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class RequestStatusServiceTest{
    @InjectMocks
    private RequestStatusServiceImpl requestStatusService;

    @Spy
    private RequestStatusRepository requestStatusRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        RequestStatus requestStatus = RequestStatus.builder()
                .id(1L)
                .requestStatus(StatusRequest.CANCELED)
                .build();

        RequestStatus requestStatus1 = RequestStatus.builder()
                .id(1L)
                .requestStatus(StatusRequest.PENDING)
                .build();

        Mockito.when(requestStatusRepository.findAll()).thenReturn(List.of(requestStatus, requestStatus1));

        List<RequestStatusDTO> actual = requestStatusService.findAll();

        Mockito.verify(requestStatusRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        RequestStatus requestStatus = RequestStatus.builder()
                .id(1L)
                .requestStatus(StatusRequest.CANCELED)
                .build();

        Mockito.when(requestStatusRepository.findById(any(Long.class))).thenReturn(requestStatus);

        RequestStatusDTO expected = RequestStatusMapper.convertToDto(requestStatus);
        RequestStatusDTO actual = requestStatusService.findById(requestStatus.getId());

        Mockito.verify(requestStatusRepository, Mockito.times(1)).findById(any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getRequestStatus(), actual.getRequestStatus());
    }

    @Test
    public void saveTest(){
        RequestStatusDTOToEntity dto = RequestStatusDTOToEntity.builder()
                .requestStatus(StatusRequest.CANCELED)
                .build();


        Mockito.doNothing().when(requestStatusRepository).save(any(RequestStatus.class));

        requestStatusService. save(dto);

        Mockito.verify(requestStatusRepository, Mockito.times(1)).save(any(RequestStatus.class));
    }

    @Test
    public void updateTest(){
        RequestStatus requestStatus = RequestStatus.builder()
                .id(1L)
                .requestStatus(StatusRequest.CANCELED)

                .build();

        RequestStatusDTOToEntity dto = new RequestStatusDTOToEntity();
        dto.setRequestStatus(StatusRequest.CANCELED);

        Mockito.when(requestStatusRepository.findById(requestStatus.getId())).thenReturn(requestStatus);

        requestStatusService.update(requestStatus.getId(), dto);

        Mockito.verify(requestStatusRepository, Mockito.times(1)).save(any(RequestStatus.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        requestStatusService.deleteById(id);
        Mockito.verify(requestStatusRepository, Mockito.times(1)).deleteById(id);
    }
}
