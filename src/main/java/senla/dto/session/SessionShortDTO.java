package senla.dto.session;

import lombok.*;
import senla.dto.client.ClientShortInfoDTO;
import senla.dto.course.CourseShortInfoDTO;
import senla.dto.order.OrderShortDTO;
import senla.dto.psychologist.PsychologistShortDTO;
import java.time.LocalDateTime;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionShortDTO {
    private PsychologistShortDTO psychologist;
    private ClientShortInfoDTO client;

    /*
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    */
    private LocalDateTime startSession;

    /*
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    */
    private LocalDateTime finalSession;

    private CourseShortInfoDTO course;
    private OrderShortDTO order;
}
