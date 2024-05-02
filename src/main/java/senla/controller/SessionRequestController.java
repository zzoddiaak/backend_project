package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;
import senla.servise.SessionRequestService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sessions-requests")
public class SessionRequestController {

    private final SessionRequestService sessionRequestService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(sessionRequestService.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
        return serialize(sessionRequestService.findById(id));
    }

    @PostMapping
    public void save(@RequestBody String sessionRequestDTO) {
        try {
            sessionRequestService.save(deserialize(sessionRequestDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody String updateDTO) {
        try {
            sessionRequestService.update(id, deserialize(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        sessionRequestService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private SessionRequestDTOToEntity deserialize(String sessionRequestDTO) throws IOException {
        return objectMapper.readValue(sessionRequestDTO, SessionRequestDTOToEntity.class);
    }
}
