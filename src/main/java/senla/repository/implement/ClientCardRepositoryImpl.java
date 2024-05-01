package senla.repository.implement;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.ClientCard;
import senla.entities.ClientCard_;
import senla.repository.api.ClientCardRepository;

import java.util.List;

@Repository
@Transactional
public class ClientCardRepositoryImpl extends AbstractRepository<Long, ClientCard> implements ClientCardRepository {
    public ClientCardRepositoryImpl() {
        super(ClientCard.class);
    }

    @Override
    public ClientCard findById(Long uuid) {
        return entityManager.find(ClientCard.class, uuid);
    }

    @Override
    public List<ClientCard> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClientCard> cq = cb.createQuery(ClientCard.class);
        Root<ClientCard> root = cq.from(ClientCard.class);
        cq.select(root);
        TypedQuery<ClientCard> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void save(ClientCard clientCard) {
        entityManager.persist(clientCard);
    }

    @Override
    public void deleteById(Long uuid) {
        ClientCard clientCard = findById(uuid);
        if (clientCard != null) {
            entityManager.remove(clientCard);
        }
    }

    @Override
    public List<ClientCard> findAllWithFetch(String recommendations) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClientCard> cq = cb.createQuery(ClientCard.class);
        Root<ClientCard> clientCardRoot = cq.from(ClientCard.class);
        cq.where(cb.equal(clientCardRoot.get(ClientCard_.recommendations), recommendations));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<ClientCard> findAllWithJoinFetch(String diagnos) {
        String jpql = "select u from ClientCard u where u.diagnos = :diagnos";
        return entityManager.createQuery(jpql, ClientCard.class)
                .setParameter("diagnos", diagnos)
                .getResultList();
    }

    @Override
    public List<ClientCard> findAllWithDetails() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClientCard> criteriaQuery = criteriaBuilder.createQuery(ClientCard.class);
        Root<ClientCard> clientCardRoot = criteriaQuery.from(ClientCard.class);

        EntityGraph<ClientCard> entityGraph = entityManager.createEntityGraph(ClientCard.class);
        entityGraph.addSubgraph("psychologist");

        TypedQuery<ClientCard> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);

        return typedQuery.getResultList();
    }
}
