package senla.entities;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestStatus extends Entity{

    private String requestStatus;
}
