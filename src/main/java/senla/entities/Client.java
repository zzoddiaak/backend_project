package senla.entities;
import lombok.*;
import jakarta.persistence.*;


@Entity
@Setter
@Getter
@Table(name = "clients")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_card_id", referencedColumnName = "id")
    private ClientCard clientCard;
}
