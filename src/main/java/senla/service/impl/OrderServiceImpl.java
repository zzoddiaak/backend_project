package senla.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.mapper.OrderMapper;
import senla.dto.order.OrderDTOToEntity;
import senla.dto.order.OrderShortDTO;
import senla.entities.Order;
import senla.repository.api.OrderRepository;
import senla.service.OrderService;

import java.util.List;
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderShortDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::convertToShortDto)
                .toList();
    }

    @Override
    public OrderShortDTO findById(long uuid) {
        Order order = orderRepository.findById(uuid);
        return order != null ? OrderMapper.convertToShortDto(order) : null;
    }

    @Override
    public boolean save(OrderDTOToEntity object) {
        Order order = OrderMapper.createOrderDto(object);
        orderRepository.save(order);
        return order.getId() != null;
    }

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

    @Override
    public void deleteById(long uuid){
        orderRepository.deleteById(uuid);
    }
}

