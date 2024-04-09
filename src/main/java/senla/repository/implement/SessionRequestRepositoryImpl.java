package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.SessionRequest;
import senla.repository.AbstractRepository;
import senla.repository.iface.SessionRequestRepository;

import java.time.LocalDate;
@Repository

public class SessionRequestRepositoryImpl extends AbstractRepository<SessionRequest> implements SessionRequestRepository {
    public SessionRequestRepositoryImpl(){
        save(SessionRequest.builder()
                .problem("Депрессия")
                .requestDate(LocalDate.of(2024, 5, 9))
                .build());
        save(SessionRequest.builder()
                .problem("Стресс")
                .requestDate(LocalDate.of(2024, 7, 1))
                .build());
    }
}
