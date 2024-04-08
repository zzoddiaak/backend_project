package senla.entities;
import lombok.*;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestStatus extends Entity {
    private Status requestStatus;

    public enum Status {
        PENDING,
        COMPLETED,
        FAILED,
        CANCELED
    }
}
