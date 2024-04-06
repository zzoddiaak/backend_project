package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.mapper.SessionMapper;
import senla.dto.session.SessionDTOToEntity;
import senla.dto.session.SessionShortDTO;
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
                .map(SessionMapper::convertToShortDTO)
                .toList();

    }
    @Override
    public SessionShortDTO findById(long uuid){
        return SessionMapper.convertToShortDTO(sessionRepository.findById(uuid));
    }
    @Override
    public void save(SessionDTOToEntity object){
        sessionRepository.save(SessionMapper.convertDTOToEntity(object));
    }
    @Override
    public void deleteById(long uuid){
        sessionRepository.deleteById(uuid);
    }
}
