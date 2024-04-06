package senla.dto.sessionrequest;

import lombok.*;
import senla.dto.client.ClientDTOToEntity;
import senla.dto.client.ClientFullInfoDTO;
import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.dto.psychologist.PsychologistFullDTO;
import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.requeststatus.RequestStatusDTOToEntity;
import senla.dto.session.SessionDTO;
import senla.dto.session.SessionDTOToEntity;

import java.time.LocalDate;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequestDTOToEntity {
    private PsychologistDTOToEntity psychologist;
    private ClientDTOToEntity client;

    /*
        @JsonFormat(pattern = "yyyy-MM-dd")
    */
    private LocalDate requestDate;

    private RequestStatusDTOToEntity requestStatus;
    private String problem;
    private SessionDTOToEntity session;
}
