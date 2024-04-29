package senla.entities;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "sessions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "start_session")
    private LocalDateTime startSession;
    @Column(name = "final_session")
    private LocalDateTime finalSession;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "psychologist_id", referencedColumnName = "id")
    private Psychologist psychologist;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

}
