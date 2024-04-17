package senla.repository.api;

import senla.entities.SessionRequest;

import java.util.List;

public interface SessionRequestRepository {
    SessionRequest findById(Long uuid);
    List<SessionRequest> findAll();
    void save(SessionRequest sessionRequest);
    void deleteById(Long uuid);
}
