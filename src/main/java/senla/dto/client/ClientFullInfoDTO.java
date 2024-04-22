package senla.dto.client;
import lombok.*;
import senla.dto.user.UserFullInfoDTO;
import senla.dto.clientcard.ClientCardDTO;
import senla.dto.user.UserFullInfoDTO;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientFullInfoDTO {
    private long uuid;
    private UserFullInfoDTO user;
    private ClientCardDTO clientCard;
}
