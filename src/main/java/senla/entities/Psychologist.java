package senla.entities;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;

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


}
