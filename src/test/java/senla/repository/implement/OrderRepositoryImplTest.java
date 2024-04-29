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
import senla.entities.Order;
import senla.entities.Role;
import senla.entities.User;
import senla.enums.RoleName;
import senla.repository.api.OrderRepository;
import senla.repository.api.UserRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class OrderRepositoryImplTest {
    @Resource
    private OrderRepository orderRepository;

    @Test
    public void findAllWithFetch() {
        Order order = Order.builder()
                .totalPrice(BigDecimal.valueOf(50.5))
                .build();

        orderRepository.save(order);

        List<Order> orders = orderRepository.findAllWithFetch(order.getTotalPrice());

        assertNotNull(orders);
        assertFalse(orders.isEmpty());

        orders.forEach(x -> assertEquals(order.getTotalPrice(), x.getTotalPrice()));
    }

    @Test
    public void findAllWithJoinFetch() {
        Order order = Order.builder()
                .discounts(BigDecimal.valueOf(69.5))
                .build();

        orderRepository.save(order);

        List<Order> orders = orderRepository.findAllWithJoinFetch(order.getDiscounts());

        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    public void findAllWithDetails() {
        Order order = Order.builder()
                .build();

        orderRepository.save(order);

        List<Order> orders = orderRepository.findAllWithDetails();

        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

}