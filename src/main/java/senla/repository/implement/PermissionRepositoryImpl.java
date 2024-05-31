package senla.repository.implement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.Permission;
import senla.entities.Role;
import senla.repository.api.PermissionRepository;

import java.util.List;

@Repository
@Transactional
public class PermissionRepositoryImpl extends AbstractRepository<Long, Permission> implements PermissionRepository {

    public PermissionRepositoryImpl() {
        super(Permission.class);
    }

    @Override
    public List<Permission> findAllWithJoinFetch(Role role) {
        String jpql = "select u from Permission u where u.role = :role";
        return entityManager.createQuery(jpql, Permission.class)
                .setParameter("role", role)
                .getResultList();
    }
    @Override
    public List<Permission> findAllWithDetails() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Permission> criteriaQuery = criteriaBuilder.createQuery(Permission.class);
        Root<Permission> permissionRoot = criteriaQuery.from(Permission.class);

        EntityGraph<Permission> entityGraph = entityManager.createEntityGraph(Permission.class);
        entityGraph.addSubgraph("role");

        TypedQuery<Permission> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);

        return typedQuery.getResultList();
    }

}
