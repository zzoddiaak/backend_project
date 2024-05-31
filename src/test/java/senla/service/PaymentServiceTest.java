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
import senla.dto.payment.PaymentDTOToEntity;
import senla.dto.payment.PaymentShortInfoDTO;
import senla.entities.Payment;
import senla.mapper.PaymentMapper;
import senla.repository.api.PaymentRepository;
import senla.service.impl.PaymentServiceImpl;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class PaymentServiceTest{
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Spy
    private PaymentRepository paymentRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        Payment payment = Payment.builder()
                .id(1L)
                .url("Test")
                .build();

        Payment payment1 = Payment.builder()
                .id(1L)
                .url("TesT")
                .build();

        Mockito.when(paymentRepository.findAll()).thenReturn(List.of(payment, payment1));

        List<PaymentShortInfoDTO> actual = paymentService.findAll();

        Mockito.verify(paymentRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        Payment payment = Payment.builder()
                .id(1L)
                .url("TesT")
                .build();

        Mockito.when(paymentRepository.findById(any(Long.class))).thenReturn(payment);

        PaymentShortInfoDTO expected = PaymentMapper.convertToShortDto(payment);
        PaymentShortInfoDTO actual = paymentService.findById(payment.getId());

        Mockito.verify(paymentRepository, Mockito.times(1)).findById(Mockito.any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getUrl(), actual.getUrl());
    }

    @Test
    public void saveTest(){
        PaymentDTOToEntity dto = PaymentDTOToEntity.builder()
                .url("TesT")
                .build();

        Payment payment = PaymentMapper.createPaymentDto(dto);

        Mockito.doNothing().when(paymentRepository).save(Mockito.any(Payment.class));

        paymentService.save(dto);

        Mockito.verify(paymentRepository, Mockito.times(1)).save(Mockito.any(Payment.class));
    }

    @Test
    public void updateTest(){
        Payment payment = Payment.builder()
                .id(1L)
                .url("TesT")
                .build();

        PaymentDTOToEntity dto = new PaymentDTOToEntity();
        dto.setUrl("TEST");

        Mockito.when(paymentRepository.findById(payment.getId())).thenReturn(payment);

        paymentService.update(payment.getId(), dto);

        Mockito.verify(paymentRepository, Mockito.times(1)).save(Mockito.any(Payment.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        paymentService.deleteById(id);
        Mockito.verify(paymentRepository, Mockito.times(1)).deleteById(id);
    }
}
