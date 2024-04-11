package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.requeststatus.RequestStatusDTOToEntity;
import senla.servise.RequestStatusService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class RequestStatusController {
    private final RequestStatusService requestStatusService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(requestStatusService.findAll());
    }

    @GetMapping("/{uuid}")
    public String findById(@PathVariable Long uuid) {
        return serialize(requestStatusService.findById(uuid));
    }

    @PostMapping
    public void save(@RequestBody String requestStatusDTO) {
        try {
            requestStatusService.save(deserialize(requestStatusDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody String updateDTO) {
        try {
            requestStatusService.update(id, deserialize(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        requestStatusService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private RequestStatusDTOToEntity deserialize(String requestStatusDTO) throws IOException {
        return objectMapper.readValue(requestStatusDTO, RequestStatusDTOToEntity.class);
    }
}
