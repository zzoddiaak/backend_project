package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.servise.PsychologistService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PsychologistController {
    private final PsychologistService psychologistService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(psychologistService.findAll());
    }

    @GetMapping("/{uuid}")
    public String findById(@PathVariable Long uuid) {
        return serialize(psychologistService.findById(uuid));
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
    public void update(@PathVariable long id, @RequestBody String updateDTO) {
        try {
            psychologistService.update(id, deserialize(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
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
