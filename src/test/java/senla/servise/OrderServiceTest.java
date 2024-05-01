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
import senla.dto.order.OrderDTOToEntity;
import senla.dto.order.OrderShortDTO;
import senla.entities.Order;
import senla.mapper.OrderMapper;
import senla.repository.api.OrderRepository;
import senla.servise.implement.OrderServiceImpl;


import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class OrderServiceTest{
    @InjectMocks
    private OrderServiceImpl orderService;

    @Spy
    private OrderRepository orderRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        Order order = Order.builder()
                .id(1L)
                .discounts(BigDecimal.valueOf(5.2))
                .build();

        Order order1 = Order.builder()
                .id(1L)
                .discounts(BigDecimal.valueOf(7.2))
                .build();

        Mockito.when(orderRepository.findAll()).thenReturn(List.of(order, order1));

        List<OrderShortDTO> actual = orderService.findAll();

        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        Order order = Order.builder()
                .id(1L)
                .discounts(BigDecimal.valueOf(5.2))
                .build();

        Mockito.when(orderRepository.findById(any(Long.class))).thenReturn(order);

        OrderShortDTO expected = OrderMapper.convertToShortDto(order);
        OrderShortDTO actual = orderService.findById(order.getId());

        Mockito.verify(orderRepository, Mockito.times(1)).findById(Mockito.any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getDiscounts(), actual.getDiscounts());
    }

    @Test
    public void saveTest(){
        OrderDTOToEntity dto = OrderDTOToEntity.builder()
                .discounts(BigDecimal.valueOf(5.2))

                .build();

        Order clientCard = OrderMapper.createOrderDto(dto);

        Mockito.doNothing().when(orderRepository).save(Mockito.any(Order.class));

        orderService.save(dto);

        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(Order.class));
    }

    @Test
    public void updateTest(){
        Order order = Order.builder()
                .id(1L)
                .discounts(BigDecimal.valueOf(5.2))

                .build();

        OrderDTOToEntity dto = new OrderDTOToEntity();
        dto.setDiscounts(BigDecimal.valueOf(8.2));

        Mockito.when(orderRepository.findById(order.getId())).thenReturn(order);

        orderService.update(order.getId(), dto);

        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(Order.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        orderService.deleteById(id);
        Mockito.verify(orderRepository, Mockito.times(1)).deleteById(id);
    }
}
