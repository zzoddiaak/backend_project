package senla.dto.sessionrequest;

import java.time.LocalDate;
import lombok.*;
import senla.dto.client.ClientShortInfoDTO;
import senla.dto.psychologist.PsychologistShortDTO;
import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.session.SessionDTO;
import senla.dto.session.SessionShortDTO;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SessionRequestDTO {
    private PsychologistShortDTO psychologist;
    private ClientShortInfoDTO client;

/*
    @JsonFormat(pattern = "yyyy-MM-dd")
*/
    private LocalDate requestDate;

    private RequestStatusDTO requestStatus;
    private String problem;
    private SessionShortDTO session;
}
