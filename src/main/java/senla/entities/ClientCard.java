package senla.entities;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCard extends Entity{

    private Psychologist psychologist;

    private String diagnos;

    private String recommendations;
}
