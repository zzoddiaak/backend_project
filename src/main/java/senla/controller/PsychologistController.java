package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.servise.PsychologistService;


@RestController
@RequiredArgsConstructor
public class PsychologistController {
    private final PsychologistService psychologistService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((psychologistService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(psychologistService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String psychologistDTO){
        psychologistService.save(objectMapper.readValue(psychologistDTO, PsychologistDTOToEntity.class));
    }
    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){psychologistService.deleteById(uuid);}
}
