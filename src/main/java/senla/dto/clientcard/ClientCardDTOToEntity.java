package senla.dto.clientcard;

import lombok.*;
import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.dto.psychologist.PsychologistShortDTO;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCardDTOToEntity {

    private PsychologistDTOToEntity psychologist;

    private String diagnosis;
    private String recommendations;
}
