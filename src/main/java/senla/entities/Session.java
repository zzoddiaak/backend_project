package senla.entities;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session extends Entity{

    private Psychologist psychologist;

    private Client client;

    private LocalDateTime startSession;

    private LocalDateTime finalSession;

    private Course course;

    private Order order;

}
