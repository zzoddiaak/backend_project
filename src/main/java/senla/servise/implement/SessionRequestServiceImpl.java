package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.mapper.SessionRequestMapper;
import senla.dto.sessionrequest.SessionRequestDTO;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;
import senla.entities.SessionRequest;
import senla.repository.api.SessionRequestRepository;
import senla.servise.SessionRequestService;
import java.util.List;
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class SessionRequestServiceImpl implements SessionRequestService {
    private final SessionRequestRepository sessionRequestRepository;
    @Transactional
    @Override
    public List<SessionRequestDTO> findAll() {
        return sessionRequestRepository.findAll().stream()
                .map(SessionRequestMapper::convertToShortDto)
                .toList();
    }
    @Transactional
    @Override
    public SessionRequestDTO findById(long uuid) {
        SessionRequest sessionRequest = sessionRequestRepository.findById(uuid);
        return sessionRequest != null ? SessionRequestMapper.convertToShortDto(sessionRequest) : null;
    }
    @Transactional
    @Override
    public boolean save(SessionRequestDTOToEntity object) {
        SessionRequest sessionRequest = SessionRequestMapper.createSessionRequestDto(object);
        sessionRequestRepository.save(sessionRequest);
        return true;
    }
    @Transactional
    @Override
    public boolean update(long uuid, SessionRequestDTOToEntity updateDTO) {
        SessionRequest sessionRequest = sessionRequestRepository.findById(uuid);
        if (sessionRequest != null) {
            if (updateDTO.getRequestDate() != null) {
                sessionRequest.setRequestDate(updateDTO.getRequestDate());
            }
            if (!updateDTO.getProblem().isEmpty()) {
                sessionRequest.setProblem(updateDTO.getProblem());
            }
            sessionRequestRepository.save(sessionRequest);
            return true;
        }
        return false;
    }
    @Transactional
    @Override
    public void deleteById(long uuid){
        sessionRequestRepository.deleteById(uuid);
    }
}

