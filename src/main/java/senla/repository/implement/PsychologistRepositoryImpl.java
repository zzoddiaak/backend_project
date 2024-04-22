package senla.repository.implement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.Psychologist;
import senla.entities.Psychologist_;
import senla.repository.api.PsychologistRepository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class PsychologistRepositoryImpl extends AbstractRepository<Long, Psychologist> implements PsychologistRepository {

    public PsychologistRepositoryImpl() {
        super(Psychologist.class);
    }
    @Override
    public List<Psychologist> findAllWithFetch(int experience) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Psychologist> cq = cb.createQuery(Psychologist.class);
        Root<Psychologist> psychologistRoot = cq.from(Psychologist.class);

        cq.where(cb.equal(psychologistRoot.get(Psychologist_.experience), experience));

        return entityManager.createQuery(cq).getResultList();
    }
    @Override
    public List<Psychologist> findAllWithJoinFetch(BigDecimal hourlyRate) {
        String jpql = "select u from Psychologist u where u.hourlyRate = :hourlyRate";
        return entityManager.createQuery(jpql, Psychologist.class)
                .setParameter("hourlyRate", hourlyRate)
                .getResultList();
    }
    @Override
    public List<Psychologist> findAllWithDetails() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Psychologist> criteriaQuery = criteriaBuilder.createQuery(Psychologist.class);
        Root<Psychologist> psychologistRoot = criteriaQuery.from(Psychologist.class);

        EntityGraph<Psychologist> entityGraph = entityManager.createEntityGraph(Psychologist.class);
        entityGraph.addSubgraph("user");

        TypedQuery<Psychologist> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);

        return typedQuery.getResultList();
    }

}
