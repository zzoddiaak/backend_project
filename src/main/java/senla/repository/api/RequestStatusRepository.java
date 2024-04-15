package senla.repository.api;

import senla.entities.RequestStatus;

import java.util.List;

public interface RequestStatusRepository {
    RequestStatus findById(Long uuid);
    List<RequestStatus> findAll();
    void save(RequestStatus requestStatus);
    void deleteById(Long uuid);
}
