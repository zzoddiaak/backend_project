package senla.service;

import senla.dto.order.OrderDTOToEntity;
import senla.dto.order.OrderShortDTO;

import java.util.List;

public interface OrderService {
    List<OrderShortDTO> findAll();
    OrderShortDTO findById(long uuid);
    boolean save(OrderDTOToEntity object);
    boolean update(long uuid, OrderDTOToEntity updateDTO);
    void deleteById(long uuid);

}

