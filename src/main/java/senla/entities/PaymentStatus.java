package senla.entities;

import lombok.*;
import senla.enums.StatusPayment;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatus extends Entity {
    private StatusPayment status;
}
