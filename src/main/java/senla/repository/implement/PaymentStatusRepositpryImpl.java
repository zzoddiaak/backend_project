package senla.repository.implement;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.*;

import senla.enums.StatusPayment;
import senla.repository.api.PaymentStatusRepository;

import java.util.List;

@Repository
@Transactional
public class PaymentStatusRepositpryImpl extends AbstractRepository<Long,PaymentStatus> implements PaymentStatusRepository {

    public PaymentStatusRepositpryImpl() {
        super(PaymentStatus.class);
    }

    @Override
    public List<PaymentStatus> findAllWithFetch(StatusPayment status) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentStatus> cq = cb.createQuery(PaymentStatus.class);
        Root<PaymentStatus> paymentStatusRoot = cq.from(PaymentStatus.class);

        cq.where(cb.equal(paymentStatusRoot.get(PaymentStatus_.status), status));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<PaymentStatus> findAllWithJoinFetch(StatusPayment status) {
        String jpql = "select u from PaymentStatus u where u.status = :status";
        return entityManager.createQuery(jpql, PaymentStatus.class)
                .setParameter("status", status)
                .getResultList();
    }

}
