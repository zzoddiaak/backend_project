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
import senla.entities.Payment;
import senla.entities.Role;
import senla.entities.User;
import senla.enums.RoleName;
import senla.repository.api.PaymentRepository;
import senla.repository.api.UserRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class PaymentRepositoryImplTest {
    @Resource
    private PaymentRepository paymentRepository;
    @Resource
    private UserRepository userRepository;

    @Test
    public void findAllWithFetch() {
        Payment payment = Payment.builder()
                .url("TEST")
                .build();

        paymentRepository.save(payment);

        List<Payment> payments = paymentRepository.findAllWithFetch(payment.getUrl());

        assertNotNull(payments);
        assertFalse(payments.isEmpty());

        payments.forEach(x -> assertEquals(payment.getUrl(), x.getUrl()));
    }

    @Test
    public void findAllWithJoinFetch() {
        User user = User.builder()
                .build();
        userRepository.save(user);
        Payment payment = Payment.builder()
                .user(user)
                .build();

        paymentRepository.save(payment);

        List<Payment> payments = paymentRepository.findAllWithJoinFetch(payment.getUser());

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

    @Test
    public void findAllWithDetails() {
        Payment payment = Payment.builder()
                .build();

        paymentRepository.save(payment);

        List<Payment> payments = paymentRepository.findAllWithDetails();

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

}