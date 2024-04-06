package senla.dto.session;

import java.time.LocalDateTime;
import lombok.*;
import senla.dto.client.ClientFullInfoDTO;
import senla.dto.course.CourseFullInfoDTO;
import senla.dto.order.OrderFullDTO;
import senla.dto.psychologist.PsychologistFullDTO;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {
    private long uuid;
    private PsychologistFullDTO psychologist;
    private ClientFullInfoDTO client;

/*
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
*/
    private LocalDateTime startSession;

/*
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
*/
    private LocalDateTime finalSession;

    private CourseFullInfoDTO course;
    private OrderFullDTO order;
}
