package senla.repository.implement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.*;
import senla.enums.RoleName;
import senla.exception.RoleNotFoundException;
import senla.repository.api.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RoleRepositoryImpl extends AbstractRepository<Long, Role> implements RoleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public RoleRepositoryImpl() {
        super(Role.class);
    }
    @Override
    public List<Role> findAllWithFetch(List<Permission> permissions) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);
        Root<Role> roleRoot = cq.from(Role.class);

        List<Predicate> predicates = new ArrayList<>();
        for (Permission permission : permissions) {
            predicates.add(cb.isMember(permission, roleRoot.get(Role_.permission)));
        }

        Predicate combinedPredicate = cb.or(predicates.toArray(new Predicate[0]));

        cq.where(combinedPredicate);

        return entityManager.createQuery(cq).getResultList();
    }

    @Transactional
    public Role findByRoleName(RoleName roleName) {
        String jpql = "SELECT r FROM Role r LEFT JOIN FETCH r.permission WHERE r.roleName = :roleName";

        List<Role> roles = entityManager.createQuery(jpql, Role.class)
                .setParameter("roleName", roleName)
                .getResultList();

        if (roles.isEmpty())
            throw new RoleNotFoundException(String.valueOf(roleName));

        return roles.get(0);
    }




    @Override
    public List<Role> findAllWithDetails() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);

        EntityGraph<Role> entityGraph = entityManager.createEntityGraph(Role.class);
        entityGraph.addSubgraph("permission");

        TypedQuery<Role> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);

        return typedQuery.getResultList();
    }


}
