package senla.entities;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "clients_cards")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "diagnos")
    private String diagnos;
    @Column(name = "recommendations")
    private String recommendations;
    @ManyToOne(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "psychologist_id", referencedColumnName = "id")
    private Psychologist psychologist;
}
