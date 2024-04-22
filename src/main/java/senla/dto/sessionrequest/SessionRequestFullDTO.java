package senla.dto.sessionrequest;

import lombok.*;
import senla.dto.client.ClientFullInfoDTO;
import senla.dto.psychologist.PsychologistFullDTO;
import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.session.SessionDTO;

import java.time.LocalDate;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequestFullDTO {
    private PsychologistFullDTO psychologist;
    private ClientFullInfoDTO client;

    private LocalDate requestDate;

    private RequestStatusDTO requestStatus;
    private String problem;
    private SessionDTO session;
}
