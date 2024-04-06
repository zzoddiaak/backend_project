package senla.entities;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatus extends Entity{
    private String statusName;
}
