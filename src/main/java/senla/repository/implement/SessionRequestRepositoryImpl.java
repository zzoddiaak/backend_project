package senla.repository.implement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.SessionRequest;
import senla.entities.SessionRequest_;
import senla.repository.api.SessionRequestRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class SessionRequestRepositoryImpl extends AbstractRepository<Long, SessionRequest> implements SessionRequestRepository {
    public SessionRequestRepositoryImpl() {
        super(SessionRequest.class);
    }
    @Override
    public List<SessionRequest> findAllWithFetch(String problem) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SessionRequest> cq = cb.createQuery(SessionRequest.class);
        Root<SessionRequest> sessionRequestRoot = cq.from(SessionRequest.class);

        cq.where(cb.equal(sessionRequestRoot.get(SessionRequest_.problem), problem));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<SessionRequest> findAllWithJoinFetch(LocalDate requestDate) {
        String jpql = "select u from SessionRequest u where u.requestDate = :requestDate";
        return entityManager.createQuery(jpql, SessionRequest.class)
                .setParameter("requestDate", requestDate)
                .getResultList();
    }

    @Override
    public List<SessionRequest> findAllWithDetails() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SessionRequest> criteriaQuery = criteriaBuilder.createQuery(SessionRequest.class);

        EntityGraph<SessionRequest> entityGraph = entityManager.createEntityGraph(SessionRequest.class);
        entityGraph.addSubgraph("session");
        entityGraph.addSubgraph("requestStatus");
        entityGraph.addSubgraph("client");
        entityGraph.addSubgraph("psychologist");

        TypedQuery<SessionRequest> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);

        return typedQuery.getResultList();
    }

}
