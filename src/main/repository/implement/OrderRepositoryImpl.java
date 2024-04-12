package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.Order;
import senla.repository.AbstractRepository;
import senla.repository.iface.OrderRepository;

import java.math.BigDecimal;

@Repository

public class OrderRepositoryImpl extends AbstractRepository<Order> implements OrderRepository {
    public OrderRepositoryImpl(){
        save(Order.builder()
                .discounts(new BigDecimal(23.6))
                .totalPrice(new BigDecimal(26.6))
                .build());
        save(Order.builder()
                .discounts(new BigDecimal(13.6))
                .totalPrice(new BigDecimal(36.6))
                .build());
    }
}
