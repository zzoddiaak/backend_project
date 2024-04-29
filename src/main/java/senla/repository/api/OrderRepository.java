package senla.repository.api;

import senla.entities.Order;
import senla.entities.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository {
    Order findById(Long uuid);
    List<Order> findAll();
    void save(Order order);
    void deleteById(Long uuid);
    public List<Order> findAllWithFetch(BigDecimal totalPrice);
    public List<Order> findAllWithJoinFetch(BigDecimal discounts);
    public List<Order> findAllWithDetails();
}
