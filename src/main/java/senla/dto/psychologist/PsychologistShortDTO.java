package senla.dto.psychologist;

import lombok.*;
import senla.dto.user.UserFullInfoDTO;
import senla.dto.user.UserShortInfoDTO;

import java.math.BigDecimal;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PsychologistShortDTO {
    private UserShortInfoDTO user;
    private int experience;
    private BigDecimal hourlyRate;
    private int ratingValue;
}
