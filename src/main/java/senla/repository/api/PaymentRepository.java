package senla.repository.api;

import senla.entities.Payment;
import senla.entities.PaymentStatus;
import senla.entities.User;

import java.util.List;

public interface PaymentRepository {
    Payment findById(Long uuid);
    List<Payment> findAll();
    void save(Payment  payment);
    void deleteById(Long uuid);
    public List<Payment> findAllWithFetch(String url);
    public List<Payment> findAllWithJoinFetch(User user);
    public List<Payment> findAllWithDetails();
}
