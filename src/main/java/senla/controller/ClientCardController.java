package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.clientcard.ClientCardDTOToEntity;
import senla.servise.ClientCardService;

@RequestMapping("/api/v1/clients-cards")
@RestController
@RequiredArgsConstructor
public class ClientCardController {
    private final ClientCardService clientCardService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(clientCardService.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id) {
        return serialize(clientCardService.findById(id));
    }

    @PostMapping
    public void save(@RequestBody String clientCardDTO) {
        try {
            clientCardService.save(deserialize(clientCardDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody String updateDTO) {
        try {
            clientCardService.update(id, deserialize(updateDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clientCardService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ClientCardDTOToEntity deserialize(String clientCardDTO) {
        try {
            return objectMapper.readValue(clientCardDTO, ClientCardDTOToEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
