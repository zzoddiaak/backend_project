package senla.service;

import senla.dto.session.SessionDTOToEntity;
import senla.dto.session.SessionShortDTO;

import java.util.List;

public interface SessionService {
    List<SessionShortDTO> findAll();
    SessionShortDTO findById(long uuid);
    boolean save(SessionDTOToEntity object);
    boolean update(long uuid, SessionDTOToEntity updateDTO);
    void deleteById(long uuid);
}
