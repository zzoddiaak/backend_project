package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.PaymentStatus.PaymentStatusDTOToEntity;
import senla.servise.PaymentStatusService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PaymentStatusController {
    private final PaymentStatusService paymentStatusService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(paymentStatusService.findAll());
    }

    @GetMapping("/{uuid}")
    public String findById(@PathVariable Long uuid) {
        return serialize(paymentStatusService.findById(uuid));
    }

    @PostMapping
    public void save(@RequestBody String paymentStatusDTO) {
        try {
            paymentStatusService.save(deserialize(paymentStatusDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody String updateDTO) {
        try {
            paymentStatusService.update(id, deserialize(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        paymentStatusService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PaymentStatusDTOToEntity deserialize(String paymentStatusDTO) throws IOException {
        return objectMapper.readValue(paymentStatusDTO, PaymentStatusDTOToEntity.class);
    }
}