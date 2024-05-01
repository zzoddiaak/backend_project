package senla.entities;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "psychologists")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Psychologist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "experience")
    private int experience;
    @Column(name = "hourly_rate")
    private BigDecimal hourlyRate;
    @Column(name = "rating_value")
    private int ratingValue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE},
            mappedBy = "psychologist"
    )
    private List<ClientCard> clientCards;
    @OneToMany(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE},
            mappedBy = "psychologist"
    )
    List<Order> orders;
    @OneToMany(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE},
            mappedBy = "psychologist"
    )
    private List<Session> sessions;
    @OneToMany(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE},
            mappedBy = "psychologist"
    )
    private List<SessionRequest> sessionRequests;



}
