package senla.entities;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "sessions_requests")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "problem")
    private String problem;

    @Column(name = "request_date")
    private LocalDate requestDate;


    @ManyToOne(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "psychologist_id", referencedColumnName = "id")
    private Psychologist psychologist;

    @ManyToOne(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_status_id", referencedColumnName = "id")
    private RequestStatus requestStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Session session;

}
