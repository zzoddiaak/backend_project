package senla.dto.psychologist;

import lombok.*;
import senla.dto.user.UserDTOToEntity;
import senla.dto.user.UserShortInfoDTO;

import java.math.BigDecimal;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PsychologistDTOToEntity {
    private UserDTOToEntity user;
    private int experience;
    private BigDecimal hourlyRate;
    private int ratingValue;
}
