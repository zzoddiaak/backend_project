package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.mapper.OrderMapper;
import senla.dto.order.OrderDTOToEntity;
import senla.dto.order.OrderShortDTO;
import senla.entities.Order;
import senla.repository.iface.OrderRepository;
import senla.servise.OrderService;
import senla.transarction.Transaction;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Transaction
    @Override
    public List<OrderShortDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::convertToShortDto)
                .toList();
    }
    @Transaction
    @Override
    public OrderShortDTO findById(long uuid) {
        Order order = orderRepository.findById(uuid);
        return order != null ? OrderMapper.convertToShortDto(order) : null;
    }
    @Transaction
    @Override
    public boolean save(OrderDTOToEntity object) {
        Order order = OrderMapper.createOrderDto(object);
        orderRepository.save(order);
        return order.getId() != null;
    }
    @Transaction
    @Override
    public boolean update(long uuid, OrderDTOToEntity updateDTO) {
        Order order = orderRepository.findById(uuid);
        if (order != null) {
            if (updateDTO.getDiscounts() != null) order.setDiscounts(updateDTO.getDiscounts());
            if (updateDTO.getTotalPrice() != null) order.setTotalPrice(updateDTO.getTotalPrice());
            orderRepository.save(order);
            return true;
        }
        return false;
    }
    @Transaction
    @Override
    public void deleteById(long uuid){
        orderRepository.deleteById(uuid);
    }
}
