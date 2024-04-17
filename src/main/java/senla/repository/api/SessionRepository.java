package senla.repository.api;

import senla.entities.Session;

import java.util.List;

public interface SessionRepository {
    Session findById(Long uuid);
    List<Session> findAll();
    void save(Session session);
    void deleteById(Long uuid);
}
