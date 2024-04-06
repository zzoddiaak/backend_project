package senla.servise;

import senla.dto.order.OrderDTOToEntity;
import senla.dto.order.OrderShortDTO;

import java.util.List;

public interface OrderService {
    List<OrderShortDTO> findAll();
    OrderShortDTO findById(long uuid);
    void save(OrderDTOToEntity object);
    void deleteById(long uuid);
}
