package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.requeststatus.RequestStatusDTOToEntity;
import senla.servise.RequestStatusService;

@RestController
@RequiredArgsConstructor
public class RequestStatusController {
    private final RequestStatusService requestStatusService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((requestStatusService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(requestStatusService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String requestStatusDTO){
        requestStatusService.save(objectMapper.readValue(requestStatusDTO, RequestStatusDTOToEntity.class));
    }
    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){requestStatusService.deleteById(uuid);}
}
