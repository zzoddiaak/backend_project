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
import senla.dto.PaymentStatus.PaymentStatusDTO;
import senla.dto.PaymentStatus.PaymentStatusDTOToEntity;
import senla.entities.PaymentStatus;
import senla.enums.StatusPayment;
import senla.mapper.PaymentStatusMapper;
import senla.repository.api.PaymentStatusRepository;
import senla.service.impl.PaymentStatusServiceImpl;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class PaymentStatusServiceTest{
    @InjectMocks
    private PaymentStatusServiceImpl paymentService;

    @Spy
    private PaymentStatusRepository paymentStatusRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        PaymentStatus paymentStatus = PaymentStatus.builder()
                .id(1L)
                .status(StatusPayment.PENDING)
                .build();

        PaymentStatus paymentStatus1 = PaymentStatus.builder()
                .id(1L)

                .build();

        Mockito.when(paymentStatusRepository.findAll()).thenReturn(List.of(paymentStatus, paymentStatus1));

        List<PaymentStatusDTO> actual = paymentService.findAll();

        Mockito.verify(paymentStatusRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        PaymentStatus paymentStatus = PaymentStatus.builder()
                .id(1L)
                .status(StatusPayment.PENDING)
                .build();

        Mockito.when(paymentStatusRepository.findById(any(Long.class))).thenReturn(paymentStatus);
        PaymentStatusDTO expected = PaymentStatusMapper.convertToDto(paymentStatus);
        PaymentStatusDTO actual = paymentService.findById(paymentStatus.getId());

        Mockito.verify(paymentStatusRepository, Mockito.times(1)).findById(any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getStatusPayment(), actual.getStatusPayment());
    }

    @Test
    public void saveTest(){
        PaymentStatusDTOToEntity dto = PaymentStatusDTOToEntity.builder()
                .statusPayment(StatusPayment.PENDING)
                .build();

        PaymentStatus paymentStatus = PaymentStatusMapper.createPaymentStatusDto(dto);

        Mockito.doNothing().when(paymentStatusRepository).save(any(PaymentStatus.class));

        paymentService. save(dto);

        Mockito.verify(paymentStatusRepository, Mockito.times(1)).save(any(PaymentStatus.class));
    }

    @Test
    public void updateTest(){
        PaymentStatus paymentStatus = PaymentStatus.builder()
                .id(1L)
                .status(StatusPayment.CANCELED)
                .build();

        PaymentStatusDTOToEntity dto = new PaymentStatusDTOToEntity();
        dto.setStatusPayment(StatusPayment.PENDING);

        Mockito.when(paymentStatusRepository.findById(paymentStatus.getId())).thenReturn(paymentStatus);

        paymentService.update(paymentStatus.getId(), dto);

        Mockito.verify(paymentStatusRepository, Mockito.times(1)).save(any(PaymentStatus.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        paymentService.deleteById(id);
        Mockito.verify(paymentStatusRepository, Mockito.times(1)).deleteById(id);
    }
}
