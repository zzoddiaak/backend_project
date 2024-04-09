package senla.servise;

import senla.dto.session.SessionDTOToEntity;
import senla.dto.session.SessionShortDTO;
import java.util.List;

public interface SessionService {
    List<SessionShortDTO> findAll();
    SessionShortDTO findById(long uuid);
    void save(SessionDTOToEntity object);
    void update(long uuid, SessionDTOToEntity updateDTO);

    void deleteById(long uuid);
}
