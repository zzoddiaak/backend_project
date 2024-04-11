package senla.dto.PaymentStatus;

import lombok.*;
import senla.enums.StatusPayment;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusDTO {
    private StatusPayment statusPayment;
}
