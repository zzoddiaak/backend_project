package senla.service;

import senla.dto.sessionrequest.SessionRequestDTOToEntity;

public interface SessionRequestService {
    void requestSession(SessionRequestDTOToEntity sessionRequestDTO);
}

