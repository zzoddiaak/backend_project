package senla.repository.api;

import senla.entities.Order;

import java.util.List;

public interface OrderRepository {
    Order findById(Long uuid);
    List<Order> findAll();
    void save(Order order);
    void deleteById(Long uuid);
}
