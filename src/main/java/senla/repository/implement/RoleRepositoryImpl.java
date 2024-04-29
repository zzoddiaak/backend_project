package senla.repository.implement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.*;
import senla.enums.RoleName;
import senla.repository.api.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RoleRepositoryImpl extends AbstractRepository<Long, Role> implements RoleRepository {

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

    @Override
    public List<Role> findAllWithJoinFetch(RoleName roleName) {
        String jpql = "select u from Role u where u.roleName = :roleName";
        return entityManager.createQuery(jpql, Role.class)
                .setParameter("roleName", roleName)
                .getResultList();
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
