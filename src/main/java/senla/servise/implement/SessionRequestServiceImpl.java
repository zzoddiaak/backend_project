package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.mapper.SessionRequestMapper;
import senla.dto.sessionrequest.SessionRequestDTO;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;
import senla.entities.SessionRequest;
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
                .map(SessionRequestMapper::convertToShortDto)
                .toList();

    }
    @Override
    public SessionRequestDTO findById(long uuid){
        return SessionRequestMapper.convertToShortDto(sessionRequestRepository.findById(uuid));
    }
    @Override
    public void save(SessionRequestDTOToEntity object){
        sessionRequestRepository.save(SessionRequestMapper.createSessionRequestDto(object));
    }
    @Override
    public void update(long uuid, SessionRequestDTOToEntity updateDTO){
        SessionRequest sessionRequest = sessionRequestRepository.findById(uuid);
        if (updateDTO.getRequestDate() != null) sessionRequest.setRequestDate(updateDTO.getRequestDate());
        if (!updateDTO.getProblem().isEmpty()) sessionRequest.setProblem(updateDTO.getProblem());
    }
    @Override
    public void deleteById(long uuid){
        sessionRequestRepository.deleteById(uuid);
    }
}
