package senla.repository.implement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.Session;
import senla.entities.Session_;
import senla.repository.api.SessionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Repository
public class SessionRepositoryImpl extends AbstractRepository<Long, Session> implements SessionRepository {
    public SessionRepositoryImpl() {
        super(Session.class);
    }
    @Override
    public List<Session> findAllWithFetch(LocalDateTime startSession) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Session> cq = cb.createQuery(Session.class);
        Root<Session> sessionRequestRoot = cq.from(Session.class);

        cq.where(cb.equal(sessionRequestRoot.get(Session_.startSession), startSession));

        return entityManager.createQuery(cq).getResultList();
    }
    @Override
    public List<Session> findAllWithJoinFetch(LocalDateTime finalSession) {
        String jpql = "select u from Session u where u.finalSession = :finalSession";
        return entityManager.createQuery(jpql, Session.class)
                .setParameter("finalSession", finalSession)
                .getResultList();
    }
    @Override
    public List<Session> findAllWithDetails() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Session> criteriaQuery = criteriaBuilder.createQuery(Session.class);
        Root<Session> sessionRoot = criteriaQuery.from(Session.class);

        EntityGraph<Session> entityGraph = entityManager.createEntityGraph(Session.class);
        entityGraph.addSubgraph("client");
        entityGraph.addSubgraph("course");
        entityGraph.addSubgraph("order");
        entityGraph.addSubgraph("psychologist");

        TypedQuery<Session> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);

        return typedQuery.getResultList();
    }

}
