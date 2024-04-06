package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.payment.PaymentDTOToEntity;
import senla.servise.PaymentService;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((paymentService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(paymentService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String paymentStatusDTO){
        paymentService.save(objectMapper.readValue(paymentStatusDTO, PaymentDTOToEntity.class));
    }
    @PutMapping("/id")
    @SneakyThrows
    public void update(@PathVariable long uuid, @RequestBody String updateDTO){
        paymentService.update(uuid, objectMapper.readValue(updateDTO, PaymentDTOToEntity.class));
    }
    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){paymentService.deleteById(uuid);}
}
