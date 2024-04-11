package senla.dto.session;

import lombok.*;
import senla.dto.client.ClientDTOToEntity;
import senla.dto.client.ClientShortInfoDTO;
import senla.dto.course.CourseDTOToEntity;
import senla.dto.order.OrderDTOToEntity;
import senla.dto.psychologist.PsychologistDTOToEntity;

import java.time.LocalDateTime;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTOToEntity {
    private PsychologistDTOToEntity psychologist;
    private ClientDTOToEntity client;

    /*
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    */
    private LocalDateTime startSession;

    /*
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    */
    private LocalDateTime finalSession;

    private CourseDTOToEntity course;
    private OrderDTOToEntity order;
}
