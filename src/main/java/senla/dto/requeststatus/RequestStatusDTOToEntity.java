package senla.dto.requeststatus;

import lombok.*;
import senla.enums.StatusRequest;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestStatusDTOToEntity {
    private StatusRequest requestStatus;

}
