package senla.entities;
import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;

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
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "psychologists_id", referencedColumnName = "id")
    private Psychologist psychologist;

}
