package senla.servise.implement;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.mapper.OrderMapper;
import senla.dto.order.OrderDTOToEntity;
import senla.dto.order.OrderShortDTO;
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
                .map(OrderMapper::convertToShortDTO)
                .toList();

    }
    @Override
    public OrderShortDTO findById(long uuid){
        return OrderMapper.convertToShortDTO(orderRepository.findById(uuid));
    }
    @Override
    public void save(OrderDTOToEntity object){
        orderRepository.save(OrderMapper.convertDTOToEntity(object));
    }
    @Override
    public void deleteById(long uuid){
        orderRepository.deleteById(uuid);
    }
}
