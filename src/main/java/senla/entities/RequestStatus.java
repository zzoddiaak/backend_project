package senla.entities;
import lombok.*;
import senla.enums.StatusRequest;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestStatus extends Entity {
    private StatusRequest requestStatus;

}
