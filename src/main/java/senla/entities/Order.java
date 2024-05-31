package senla.entities;
import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "discounts")
    private BigDecimal discounts;


    @ManyToOne(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "psychologists_id", referencedColumnName = "id")
    private Psychologist psychologist;

    @OneToMany(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE},
            mappedBy = "order"
    )
    private List<Course> courses;
    @OneToMany(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE},
            mappedBy = "order"
    )
    private List<Payment> payments;
    @OneToMany(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE},
            mappedBy = "order"
    )
    private List<Session> sessions;

}
