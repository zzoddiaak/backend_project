package senla.repository.iface;

import senla.entities.Client;
import senla.entities.RequestStatus;

import java.util.List;

public interface RequestStatusRepository {
    RequestStatus findById(Long uuid);
    List<RequestStatus> findAll();
    void save(RequestStatus requestStatus);
    void deleteById(Long uuid);
}
