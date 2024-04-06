package senla.entities;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Entity {

    private User user;

    private ClientCard clientCard;
}
