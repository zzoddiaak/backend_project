package senla.repository.implement;


import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.User;
import senla.entities.User_;
import senla.exception.UserNotFoundException;
import senla.repository.api.UserRepository;
import java.util.List;

@Transactional
@Repository
public class UserRepositoryImpl extends AbstractRepository<Long, User> implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }
    @Override
    public List<User> findAllWithFetch(String secondName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);

        criteriaQuery.where(criteriaBuilder.equal(user.get(User_.secondname), secondName));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    @Override
    public List<User> findAllWithJoinFetch(String email) {
        String jpql = "select u from User u where u.email = :email";
        return entityManager.createQuery(jpql, User.class)
                .setParameter("email", email)
                .getResultList();
    }
    @Override
    public List<User> findAllWithDetails() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        EntityGraph<User> entityGraph = entityManager.createEntityGraph(User.class);
        entityGraph.addSubgraph("role");

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);

        return typedQuery.getResultList();
    }


    @Override
    public User findByEmail(String email) {
        final String jpql = "SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.email = :email";

        List<User> users = entityManager.createQuery(jpql, User.class)
                .setParameter("email", email)
                .getResultList();

        if (users.isEmpty()) {
            throw new UserNotFoundException(email);
        }

        return users.iterator().next();
    }

}

