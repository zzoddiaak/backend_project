package senla.entities;

import lombok.*;
import senla.enums.StatusPayment;
import jakarta.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "payments_status")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatus  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_name")
    private StatusPayment status;
}
