package senla.dto.payment;
import lombok.*;
import senla.dto.PaymentStatus.PaymentStatusDTO;
import senla.dto.order.OrderFullDTO;
import senla.dto.session.SessionDTO;
import senla.dto.user.UserFullInfoDTO;



@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFullInfoDTO {
    private long uuid;
    private UserFullInfoDTO user;
    private SessionDTO session;
    private PaymentStatusDTO paymentStatus;
    private String url;
    private OrderFullDTO order;
}
