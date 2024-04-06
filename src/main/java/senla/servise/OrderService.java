package senla.servise;

import senla.dto.order.OrderDTOToEntity;
import senla.dto.order.OrderShortDTO;

import java.util.List;

public interface OrderService {
    List<OrderShortDTO> findAll();
    OrderShortDTO findById(long uuid);
    void save(OrderDTOToEntity object);
    void update(long uuid, OrderDTOToEntity updateDTO);

    void deleteById(long uuid);
}
