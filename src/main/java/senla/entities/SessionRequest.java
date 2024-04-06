package senla.entities;

import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequest extends Entity{

    private Psychologist psychologist;

    private Client client;

    private LocalDate requestDate;

    private RequestStatus requestStatus;

    private String problem;

    private Session session;
}
