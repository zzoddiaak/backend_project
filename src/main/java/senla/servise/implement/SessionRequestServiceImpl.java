/*
package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.mapper.SessionRequestMapper;
import senla.dto.sessionrequest.SessionRequestDTO;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;
import senla.repository.iface.SessionRequestRepository;
import senla.servise.SessionRequestService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionRequestServiceImpl implements SessionRequestService {
    private final SessionRequestRepository sessionRequestRepository;
    @Override
    public List<SessionRequestDTO> findAll(){
        return sessionRequestRepository.findAll().stream()
                .map(SessionRequestMapper::convertToShortDTO)
                .toList();

    }
    @Override
    public SessionRequestDTO findById(long uuid){
        return SessionRequestMapper.convertToShortDTO(sessionRequestRepository.findById(uuid));
    }
    @Override
    public void save(SessionRequestDTOToEntity object){
        sessionRequestRepository.save(SessionRequestMapper.convertDTOToEntity(object));
    }
    @Override
    public void deleteById(long uuid){
        sessionRequestRepository.deleteById(uuid);
    }
}
*/