package senla.repository.implement;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import senla.entities.RequestStatus;
import senla.entities.RequestStatus_;
import senla.enums.StatusRequest;
import senla.repository.api.RequestStatusRepository;

import java.util.List;

@Repository
@Transactional
public class RequestStatusRepositoryImpl extends AbstractRepository<Long,RequestStatus> implements RequestStatusRepository {

    public RequestStatusRepositoryImpl() {
        super(RequestStatus.class);
    }

    @Override
    public List<RequestStatus> findAllWithFetch(StatusRequest requestStatus) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RequestStatus> cq = cb.createQuery(RequestStatus.class);
        Root<RequestStatus> requestStatusRoot = cq.from(RequestStatus.class);

        cq.where(cb.equal(requestStatusRoot.get(RequestStatus_.requestStatus), requestStatus));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<RequestStatus> findAllWithJoinFetch(StatusRequest requestStatus) {
        String jpql = "select u from RequestStatus u where u.requestStatus = :requestStatus";
        return entityManager.createQuery(jpql, RequestStatus.class)
                .setParameter("requestStatus", requestStatus)
                .getResultList();
    }

}
