package senla.dto.client;

import lombok.*;
import senla.dto.clientcard.ClientCardDTO;
import senla.dto.user.UserFullInfoDTO;
import senla.dto.user.UserShortInfoDTO;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientShortInfoDTO {
    private UserShortInfoDTO user;
    private ClientCardDTO clientCard;
}
