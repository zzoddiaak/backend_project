package senla.dto.requeststatus;
import lombok.*;
import senla.enums.StatusRequest;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestStatusDTO {
    private StatusRequest requestStatus;
}
