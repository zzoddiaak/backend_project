package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.service.PsychologistService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/psychologists")
public class PsychologistController {
    private final PsychologistService psychologistService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(psychologistService.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
        return serialize(psychologistService.findById(id));
    }

    @PostMapping
    public void save(@RequestBody String psychologistDTO) {
        try {
            psychologistService.save(deserialize(psychologistDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody String updateDTO) {
        try {
            psychologistService.update(id, deserialize(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        psychologistService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PsychologistDTOToEntity deserialize(String psychologistDTO) throws IOException {
        return objectMapper.readValue(psychologistDTO, PsychologistDTOToEntity.class);
    }
}
