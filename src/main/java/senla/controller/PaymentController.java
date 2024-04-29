package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.payment.PaymentDTOToEntity;
import senla.servise.PaymentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(paymentService.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id) {
        return serialize(paymentService.findById(id));
    }

    @PostMapping
    public void save(@RequestBody String paymentDTO) {
        try {
            paymentService.save(deserialize(paymentDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody String updateDTO) {
        try {
            paymentService.update(id, deserialize(updateDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        paymentService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private PaymentDTOToEntity deserialize(String paymentDTO) {
        try {
            return objectMapper.readValue(paymentDTO, PaymentDTOToEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
