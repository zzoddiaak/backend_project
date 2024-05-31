package senla.entities;
import lombok.*;
import senla.enums.StatusRequest;
import jakarta.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "requests_status")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestStatus  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private StatusRequest requestStatus;

}
