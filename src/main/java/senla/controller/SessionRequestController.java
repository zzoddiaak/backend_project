package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;
import senla.service.SessionRequestService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sessions-requests")
public class SessionRequestController {

    private final SessionRequestService sessionRequestService;
    private final ObjectMapper objectMapper;
    @PostMapping("/request-session")
    public void requestSession(@RequestBody String sessionRequestDTO) {
        try {
            sessionRequestService.requestSession(deserialize(sessionRequestDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SessionRequestDTOToEntity deserialize(String sessionRequestDTO) throws IOException {
        return objectMapper.readValue(sessionRequestDTO, SessionRequestDTOToEntity.class);
    }
}
