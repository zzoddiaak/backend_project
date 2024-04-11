package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.mapper.SessionMapper;
import senla.dto.session.SessionDTOToEntity;
import senla.dto.session.SessionShortDTO;
import senla.entities.Session;
import senla.repository.iface.SessionRepository;
import senla.servise.SessionService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    @Override
    public List<SessionShortDTO> findAll(){
        return sessionRepository.findAll().stream()
                .map(SessionMapper::convertToShortDto)
                .toList();

    }
    @Override
    public SessionShortDTO findById(long uuid){
        return SessionMapper.convertToShortDto(sessionRepository.findById(uuid));
    }
    @Override
    public void save(SessionDTOToEntity object){
        sessionRepository.save(SessionMapper.createSessionDto(object));
    }
    @Override
    public void update(long uuid, SessionDTOToEntity updateDTO){
        Session session = sessionRepository.findById(uuid);
        if (updateDTO.getFinalSession() != null) session.setFinalSession(updateDTO.getFinalSession());
        if (updateDTO.getStartSession() != null) session.setStartSession(updateDTO.getStartSession());
    }
    @Override
    public void deleteById(long uuid){
        sessionRepository.deleteById(uuid);
    }
}
