package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;
import senla.servise.SessionRequestService;

@RestController
@RequiredArgsConstructor
public class SessionRequestController {

    private final SessionRequestService sessionRequestService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((sessionRequestService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(sessionRequestService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String sessionRequestDTO){
        sessionRequestService.save(objectMapper.readValue(sessionRequestDTO, SessionRequestDTOToEntity.class));
    }
    @PutMapping("/id")
    @SneakyThrows
    public void update(@PathVariable long uuid, @RequestBody String updateDTO){
        sessionRequestService.update(uuid, objectMapper.readValue(updateDTO, SessionRequestDTOToEntity.class));
    }
    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){sessionRequestService.deleteById(uuid);}
}
