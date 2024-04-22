package senla.repository.implement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.Order;
import senla.entities.Order_;
import senla.repository.api.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class OrderRepositoryImpl extends AbstractRepository<Long, Order> implements OrderRepository {
    public OrderRepositoryImpl() {
        super(Order.class);
    }
    @Override
    public List<Order> findAllWithFetch(BigDecimal totalPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> orderRoot = cq.from(Order.class);

        cq.where(cb.equal(orderRoot.get(Order_.totalPrice), totalPrice));

        return entityManager.createQuery(cq).getResultList();
    }
    @Override
    public List<Order> findAllWithJoinFetch(BigDecimal discounts) {
        String jpql = "select u from Order u where u.discounts = :discounts";
        return entityManager.createQuery(jpql, Order.class)
                .setParameter("discounts", discounts)
                .getResultList();
    }
    @Override
    public List<Order> findAllWithDetails() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = criteriaQuery.from(Order.class);

        EntityGraph<Order> entityGraph = entityManager.createEntityGraph(Order.class);
        entityGraph.addSubgraph("psychologist");

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);

        return typedQuery.getResultList();
    }

}
