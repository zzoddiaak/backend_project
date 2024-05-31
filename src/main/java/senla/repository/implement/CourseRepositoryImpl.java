package senla.repository.implement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.Course;
import senla.entities.Course_;
import senla.repository.api.CourseRepository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class CourseRepositoryImpl extends AbstractRepository<Long, Course> implements CourseRepository {

    public CourseRepositoryImpl() {
        super(Course.class);
    }

    @Override
    public List<Course> findAllWithFetch(BigDecimal coursePrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);

        cq.where(cb.equal(courseRoot.get(Course_.coursePrice), coursePrice));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Course> findAllWithJoinFetch(String courseName) {
        String jpql = "select u from Course u where u.courseName = :courseName";
        return entityManager.createQuery(jpql, Course.class)
                .setParameter("courseName", courseName)
                .getResultList();
    }

    @Override
    public List<Course> findAllWithDetails() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        EntityGraph<Course> entityGraph = entityManager.createEntityGraph(Course.class);
        entityGraph.addSubgraph("order");

        TypedQuery<Course> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);

        return typedQuery.getResultList();
    }

}
