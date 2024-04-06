package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.PaymentStatus;
import senla.repository.AbstractRepository;
import senla.repository.iface.PaymentStatusRepository;
@Repository

public class PaymentStatusRepositpryImpl extends AbstractRepository<PaymentStatus> implements PaymentStatusRepository {
    public PaymentStatusRepositpryImpl(){
        save(PaymentStatus.builder()
                .statusName("оплачено")
                .build());
        save(PaymentStatus.builder()
                .statusName("оплчено")
                .build());
    }
}