package senla.dto.clientcard;
import lombok.*;
import senla.dto.psychologist.PsychologistShortDTO;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCardDTO {
    private Long id;
    private PsychologistShortDTO psychologist;
    private String diagnosis;
    private String recommendations;
}








