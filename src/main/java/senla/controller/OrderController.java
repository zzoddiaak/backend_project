package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.order.OrderDTOToEntity;
import senla.servise.OrderService;
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(orderService.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
        return serialize(orderService.findById(id));
    }

    @PostMapping
    public void save(@RequestBody String orderDTO) {
        try {
            orderService.save(deserialize(orderDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody String updateDTO) {
        try {
            orderService.update(id, deserialize(updateDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        orderService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private OrderDTOToEntity deserialize(String orderDTO) {
        try {
            return objectMapper.readValue(orderDTO, OrderDTOToEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
