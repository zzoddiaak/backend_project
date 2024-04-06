package senla.servise;



import senla.dto.sessionrequest.SessionRequestDTO;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;

import java.util.List;

public interface SessionRequestService {
    List<SessionRequestDTO> findAll();
    SessionRequestDTO findById(long uuid);
    void save(SessionRequestDTOToEntity object);
    void deleteById(long uuid);
}
