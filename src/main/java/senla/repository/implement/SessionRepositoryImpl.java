package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.Session;
import senla.repository.AbstractRepository;
import senla.repository.iface.SessionRepository;

import java.time.LocalDateTime;
@Repository

public class SessionRepositoryImpl extends AbstractRepository<Session> implements SessionRepository {
    public SessionRepositoryImpl(){
        save(Session.builder()
                .finalSession(LocalDateTime.of(2024, 4, 12, 8, 30))
                .startSession(LocalDateTime.of(2024, 4, 17, 9, 35))
                .build());
        save(Session.builder()
                .finalSession(LocalDateTime.of(2024, 4, 12, 11, 30))
                .startSession(LocalDateTime.of(2024, 4, 17, 12, 35))
                .build());
    }
}
