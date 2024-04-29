package senla.repository.api;

import senla.entities.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository {
    Session findById(Long uuid);
    List<Session> findAll();
    void save(Session session);
    void deleteById(Long uuid);
    public List<Session> findAllWithFetch(LocalDateTime startSession);
    public List<Session> findAllWithJoinFetch(LocalDateTime finalSession);

    public List<Session> findAllWithDetails();
}
