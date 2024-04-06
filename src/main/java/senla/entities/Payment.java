package senla.entities;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends Entity{

    private User user;

    private Session session;

    private PaymentStatus paymentStatus;

    private String url;

    private Order order;
}
