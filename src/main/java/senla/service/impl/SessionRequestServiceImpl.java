package senla.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.mapper.SessionRequestMapper;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;
import senla.entities.SessionRequest;
import senla.repository.api.SessionRequestRepository;
import senla.service.SessionRequestService;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class SessionRequestServiceImpl implements SessionRequestService {
    private final SessionRequestRepository sessionRequestRepository;

    @Transactional
    @Override
    public void requestSession(SessionRequestDTOToEntity sessionRequestDTO) {
        SessionRequest sessionRequest = SessionRequestMapper.createSessionRequestDto(sessionRequestDTO);

        sessionRequest.setRequestDate(sessionRequestDTO.getRequestDate());
        sessionRequest.setProblem(sessionRequestDTO.getProblem());

        sessionRequestRepository.save(sessionRequest);
    }
}

