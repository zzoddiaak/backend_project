package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.order.OrderDTOToEntity;
import senla.servise.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((orderService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(orderService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String orderDTO){
        orderService.save(objectMapper.readValue(orderDTO, OrderDTOToEntity.class));
    }
    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){orderService.deleteById(uuid);}
}
