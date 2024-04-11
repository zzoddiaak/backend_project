package senla.servise.implement;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.mapper.OrderMapper;
import senla.dto.order.OrderDTOToEntity;
import senla.dto.order.OrderShortDTO;
import senla.entities.Order;
import senla.repository.iface.OrderRepository;
import senla.servise.OrderService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public List<OrderShortDTO> findAll(){
        return orderRepository.findAll().stream()
                .map(OrderMapper::convertToShortDto)
                .toList();

    }
    @Override
    public OrderShortDTO findById(long uuid){
        return OrderMapper.convertToShortDto(orderRepository.findById(uuid));
    }
    @Override
    public void save(OrderDTOToEntity object){
        orderRepository.save(OrderMapper.createOrderDto(object));
    }
    @Override
    public void update(long uuid, OrderDTOToEntity updateDTO){
        Order order = orderRepository.findById(uuid);
        if (updateDTO.getDiscounts() != null) order.setDiscounts(updateDTO.getDiscounts());
        if (updateDTO.getTotalPrice() != null) order.setTotalPrice(updateDTO.getTotalPrice());
    }
    @Override
    public void deleteById(long uuid){
        orderRepository.deleteById(uuid);
    }
}
