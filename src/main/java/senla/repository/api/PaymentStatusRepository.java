package senla.repository.api;

import senla.entities.PaymentStatus;
import senla.entities.Permission;
import senla.enums.StatusPayment;

import java.util.List;

public interface PaymentStatusRepository {
    PaymentStatus findById(Long uuid);
    List<PaymentStatus> findAll();
    void save(PaymentStatus paymentStatus);
    void deleteById(Long uuid);
    public List<PaymentStatus> findAllWithFetch(StatusPayment status);
    public List<PaymentStatus> findAllWithJoinFetch(StatusPayment status);
}
