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
import senla.entities.PaymentStatus;
import senla.enums.StatusPayment;
import senla.repository.api.PaymentStatusRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static senla.enums.StatusPayment.PENDING;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class PaymentStatusRepositpryImplTest {
    @Resource
    private PaymentStatusRepository paymentStatusRepository;

    @Test
    public void findAllWithFetch() {
        PaymentStatus paymentStatus = PaymentStatus.builder()
                .status(PENDING)
                .build();

        paymentStatusRepository.save(paymentStatus);

        List<PaymentStatus> paymentStatuses = paymentStatusRepository.findAllWithFetch(paymentStatus.getStatus());

        assertNotNull(paymentStatuses);
        assertFalse(paymentStatuses.isEmpty());

        paymentStatuses.forEach(x -> assertEquals(paymentStatus.getStatus(), x.getStatus()));
    }

    @Test
    public void findAllWithJoinFetch() {
        PaymentStatus paymentStatus = PaymentStatus.builder()
                .status(PENDING)
                .build();

        paymentStatusRepository.save(paymentStatus);

        List<PaymentStatus> paymentStatuses = paymentStatusRepository.findAllWithJoinFetch(paymentStatus.getStatus());

        assertNotNull(paymentStatuses);
        assertFalse(paymentStatuses.isEmpty());
    }

}
