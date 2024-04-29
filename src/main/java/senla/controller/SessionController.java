package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.session.SessionDTOToEntity;
import senla.servise.SessionService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sessions")
public class SessionController {
    private final SessionService sessionService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(sessionService.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id) {
        return serialize(sessionService.findById(id));
    }

    @PostMapping
    public void save(@RequestBody String sessionDTO) {
        try {
            sessionService.save(deserialize(sessionDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody String updateDTO) {
        try {
            sessionService.update(id, deserialize(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        sessionService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private SessionDTOToEntity deserialize(String sessionDTO) throws IOException {
        return objectMapper.readValue(sessionDTO, SessionDTOToEntity.class);
    }
}
