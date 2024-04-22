package senla.repository.implement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.entities.*;
import senla.repository.api.PaymentRepository;

import java.util.List;

@Repository
public class PaymentRepositoryImpl extends AbstractRepository<Long ,Payment> implements PaymentRepository {
    public PaymentRepositoryImpl() {
        super(Payment.class);
    }
    @Override
    public List<Payment> findAllWithFetch(String url) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
        Root<Payment> paymentRoot = cq.from(Payment.class);

        cq.where(cb.equal(paymentRoot.get(Payment_.url), url));

        return entityManager.createQuery(cq).getResultList();
    }
    @Override
    public List<Payment> findAllWithJoinFetch(User user) {
        String jpql = "select u from Payment u where u.user = :user";
        return entityManager.createQuery(jpql, Payment.class)
                .setParameter("user", user)
                .getResultList();
    }
    @Override
    public List<Payment> findAllWithDetails() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Payment> criteriaQuery = criteriaBuilder.createQuery(Payment.class);
        Root<Payment> paymentRoot = criteriaQuery.from(Payment.class);
        EntityGraph<Payment> entityGraph = entityManager.createEntityGraph(Payment.class);
        entityGraph.addSubgraph("paymentStatus");
        entityGraph.addSubgraph("order");
        entityGraph.addSubgraph("session");
        entityGraph.addSubgraph("user");
        TypedQuery<Payment> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);
        return typedQuery.getResultList();
    }

}
