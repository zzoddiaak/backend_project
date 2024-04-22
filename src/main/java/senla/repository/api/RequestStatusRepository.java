package senla.repository.api;

import senla.entities.RequestStatus;
import senla.enums.StatusRequest;

import java.util.List;

public interface RequestStatusRepository {
    RequestStatus findById(Long uuid);
    List<RequestStatus> findAll();
    void save(RequestStatus requestStatus);
    void deleteById(Long uuid);
    public List<RequestStatus> findAllWithFetch(StatusRequest requestStatus);
    public List<RequestStatus> findAllWithJoinFetch(StatusRequest requestStatus);
}
