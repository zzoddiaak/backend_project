package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.PaymentStatus.PaymentStatusDTOToEntity;
import senla.servise.PaymentStatusService;

@RestController
@RequiredArgsConstructor
public class PaymentStatusController {
    private final PaymentStatusService paymentStatusService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((paymentStatusService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(paymentStatusService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String paymentStatusDTO){
        paymentStatusService.save(objectMapper.readValue(paymentStatusDTO, PaymentStatusDTOToEntity.class));
    }
    @PutMapping("/id")
    @SneakyThrows
    public void update(@PathVariable long uuid, @RequestBody String updateDTO){
        paymentStatusService.update(uuid, objectMapper.readValue(updateDTO, PaymentStatusDTOToEntity.class));
    }
    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){paymentStatusService.deleteById(uuid);}
}
