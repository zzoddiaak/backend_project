package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.mapper.SessionMapper;
import senla.dto.session.SessionDTOToEntity;
import senla.dto.session.SessionShortDTO;
import senla.entities.Session;
import senla.repository.api.SessionRepository;
import senla.repository.api.UserRepository;
import senla.servise.SessionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;

    @Override
    public List<SessionShortDTO> findAll() {
        return sessionRepository.findAll().stream()
                .map(SessionMapper::convertToShortDto)
                .toList();
    }
    @Override
    public SessionShortDTO findById(long uuid) {
        Session session = sessionRepository.findById(uuid);
        return session != null ? SessionMapper.convertToShortDto(session) : null;
    }
    @Override
    public boolean save(SessionDTOToEntity object) {
        sessionRepository.save(SessionMapper.createSessionDto(object));
        return true;
    }
    @Override
    public boolean update(long uuid, SessionDTOToEntity updateDTO) {
        Session session = sessionRepository.findById(uuid);
        if (session != null) {
            if (updateDTO.getFinalSession() != null) {
                session.setFinalSession(updateDTO.getFinalSession());
            }
            if (updateDTO.getStartSession() != null) {
                session.setStartSession(updateDTO.getStartSession());
            }
            sessionRepository.save(session);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(long uuid) {
        sessionRepository.deleteById(uuid);
    }
}
