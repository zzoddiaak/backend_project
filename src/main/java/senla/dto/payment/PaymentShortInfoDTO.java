package senla.dto.payment;

import lombok.*;
import senla.dto.PaymentStatus.PaymentStatusDTO;
import senla.dto.order.OrderShortDTO;
import senla.dto.session.SessionShortDTO;
import senla.dto.user.UserShortInfoDTO;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentShortInfoDTO {
    private UserShortInfoDTO user;
    private SessionShortDTO session;
    private PaymentStatusDTO paymentStatus;
    private String url;
    private OrderShortDTO order;
}
