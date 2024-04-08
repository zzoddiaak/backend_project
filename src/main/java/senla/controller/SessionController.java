package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.session.SessionDTOToEntity;
import senla.servise.SessionService;

@RestController
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((sessionService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(sessionService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String sessionDTO){
        sessionService.save(objectMapper.readValue(sessionDTO, SessionDTOToEntity.class));
    }
    @PutMapping("/id")
    @SneakyThrows
    public void update(@PathVariable long uuid, @RequestBody String updateDTO){
        sessionService.update(uuid, objectMapper.readValue(updateDTO, SessionDTOToEntity.class));
    }
    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){sessionService.deleteById(uuid);}
}
