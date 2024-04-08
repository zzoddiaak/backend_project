package senla.dto.psychologist;

import java.math.BigDecimal;
import lombok.*;
import senla.dto.user.UserFullInfoDTO;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PsychologistFullDTO {
    private long uuid;
    private UserFullInfoDTO user;
    private int experience;
    private BigDecimal hourlyRate;
    private int ratingValue;
}
