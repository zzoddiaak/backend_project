package senla.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senla.dto.payment.PaymentSessionDTO;
import senla.service.PaymentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PutMapping("/buy/{id}")
    public void buySession(@PathVariable("id") long id, @RequestBody PaymentSessionDTO paymentSessionDTO) {
        try {
            paymentService.buySession(paymentSessionDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
