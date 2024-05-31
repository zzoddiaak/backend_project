package senla.entities;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "payments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Session session;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_status_id", referencedColumnName = "id")
    private PaymentStatus paymentStatus;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
