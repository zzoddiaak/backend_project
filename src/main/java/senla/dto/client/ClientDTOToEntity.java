package senla.dto.client;

import lombok.*;
import senla.dto.clientcard.ClientCardDTO;
import senla.dto.clientcard.ClientCardDTOToEntity;
import senla.dto.user.UserDTOToEntity;
import senla.dto.user.UserShortInfoDTO;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTOToEntity {
    private UserDTOToEntity user;
    private ClientCardDTOToEntity clientCard;
}
