package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.RequestStatus;
import senla.enums.StatusRequest;
import senla.repository.AbstractRepository;
import senla.repository.iface.RequestStatusRepository;

@Repository
public class RequestStatusRepositoryImpl extends AbstractRepository<RequestStatus> implements RequestStatusRepository {
    public RequestStatusRepositoryImpl(){
        save(RequestStatus.builder()
                .requestStatus(StatusRequest.PENDING)
                .build());
        save(RequestStatus.builder()
                .requestStatus(StatusRequest.COMPLETED)
                .build());
    }
}
