package senla.repository.implement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.*;
import senla.repository.api.ClientRepository;

import java.util.List;

@Repository
@Transactional
public class ClientRepositoryImpl extends AbstractRepository<Long, Client> implements ClientRepository {

    public ClientRepositoryImpl() {
        super(Client.class);
    }

    @Override
    public List<Client> findAllWithDetails() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);

        EntityGraph<Client> entityGraph = entityManager.createEntityGraph(Client.class);
        entityGraph.addSubgraph("clientCard");
        entityGraph.addSubgraph("user");

        TypedQuery<Client> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);

        return typedQuery.getResultList();
    }

}
