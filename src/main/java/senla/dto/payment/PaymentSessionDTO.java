package senla.dto.payment;

import lombok.Data;

@Data
public class PaymentSessionDTO {
    private Long userId;
    private Long sessionId;
    private String url;
    private Long orderId;
}
