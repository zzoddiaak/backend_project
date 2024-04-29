package senla.repository.api;

import senla.entities.SessionRequest;
import senla.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface SessionRequestRepository {
    SessionRequest findById(Long uuid);
    List<SessionRequest> findAll();
    void save(SessionRequest sessionRequest);
    void deleteById(Long uuid);
    public List<SessionRequest> findAllWithFetch(String problem);
    public List<SessionRequest> findAllWithJoinFetch(LocalDate requestDate);

    public List<SessionRequest> findAllWithDetails();
}
