package senla.entities;
import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "courses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_price")
    private BigDecimal coursePrice;


    @ManyToOne(
            fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE}
    )
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    @OneToMany(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE},
            mappedBy = "course"
    )
    private List<Session> session;

}
