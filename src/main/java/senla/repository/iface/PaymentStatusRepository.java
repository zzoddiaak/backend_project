package senla.repository.iface;

import senla.entities.Client;
import senla.entities.PaymentStatus;

import java.util.List;

public interface PaymentStatusRepository {
    PaymentStatus findById(Long uuid);
    List<PaymentStatus> findAll();
    void save(PaymentStatus paymentStatus);
    void deleteById(Long uuid);
}
