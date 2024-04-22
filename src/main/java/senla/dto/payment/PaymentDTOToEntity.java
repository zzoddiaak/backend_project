package senla.dto.payment;

import lombok.*;
import senla.dto.PaymentStatus.PaymentStatusDTOToEntity;
import senla.dto.order.OrderDTOToEntity;

import senla.dto.session.SessionDTOToEntity;
import senla.dto.user.UserDTOToEntity;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTOToEntity {
    private UserDTOToEntity user;
    private SessionDTOToEntity session;
    private PaymentStatusDTOToEntity paymentStatus;
    private String url;
    private OrderDTOToEntity order;
}
