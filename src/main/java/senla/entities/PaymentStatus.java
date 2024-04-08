package senla.entities;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatus extends Entity {
    private Status status;

    public enum Status {
        PENDING,
        COMPLETED,
        FAILED,
        CANCELED
    }
}
