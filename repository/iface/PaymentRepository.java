package senla.repository.iface;

import senla.entities.Client;
import senla.entities.Payment;

import java.util.List;

public interface PaymentRepository {
    Payment findById(Long uuid);
    List<Payment> findAll();
    void save(Payment  payment);
    void deleteById(Long uuid);
}
