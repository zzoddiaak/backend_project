package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.Payment;
import senla.repository.AbstractRepository;
import senla.repository.iface.PaymentRepository;
@Repository
public class PaymentRepositoryImpl extends AbstractRepository<Payment> implements PaymentRepository {
    public PaymentRepositoryImpl(){
        save(Payment.builder()
                .url("dfsdsd")
                .build());
        save(Payment.builder()
                .url("ghhggh")
                .build());
    }
}
