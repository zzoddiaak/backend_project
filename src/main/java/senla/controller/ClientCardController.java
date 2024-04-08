package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.clientcard.ClientCardDTOToEntity;
import senla.servise.ClientCardService;

@RestController
@RequiredArgsConstructor
public class ClientCardController {
    private final ClientCardService clientCardService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((clientCardService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(clientCardService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String clientCardDTO){
        clientCardService.save(objectMapper.readValue(clientCardDTO, ClientCardDTOToEntity.class));
    }
    @PutMapping("/id")
    @SneakyThrows
    public void update(@PathVariable long uuid, @RequestBody String updateDTO){
        clientCardService.update(uuid, objectMapper.readValue(updateDTO, ClientCardDTOToEntity.class));
    }
    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){clientCardService.deleteById(uuid);}
}
