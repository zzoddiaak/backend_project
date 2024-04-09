package senla.dto.PaymentStatus;

import lombok.*;
import senla.enums.StatusPayment;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusDTOToEntity {
    private StatusPayment statusPayment;

}
