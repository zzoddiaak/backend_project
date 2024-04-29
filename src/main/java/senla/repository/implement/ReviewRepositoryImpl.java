package senla.repository.implement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.Review;
import senla.entities.Review_;
import senla.repository.api.ReviewRepository;

import java.util.List;

@Repository
@Transactional
public class ReviewRepositoryImpl extends AbstractRepository<Long, Review> implements ReviewRepository {
    public ReviewRepositoryImpl() {
        super(Review.class);
    }
    @Override
    public List<Review> findAllWithFetch(int rating) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> cq = cb.createQuery(Review.class);
        Root<Review> reviewRoot = cq.from(Review.class);

        cq.where(cb.equal(reviewRoot.get(Review_.rating), rating));

        return entityManager.createQuery(cq).getResultList();
    }
    @Override
    public List<Review> findAllWithJoinFetch(String commentReviews) {
        String jpql = "select u from Review u where u.commentReviews = :commentReviews";
        return entityManager.createQuery(jpql, Review.class)
                .setParameter("commentReviews", commentReviews)
                .getResultList();
    }
    @Override
    public List<Review> findAllWithDetails() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> criteriaQuery = criteriaBuilder.createQuery(Review.class);
        Root<Review> reviewRoot = criteriaQuery.from(Review.class);

        EntityGraph<Review> entityGraph = entityManager.createEntityGraph(Review.class);
        entityGraph.addSubgraph("session");

        TypedQuery<Review> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);

        return typedQuery.getResultList();
    }

}
