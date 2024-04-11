package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.user.UserDTOToEntity;
import senla.servise.UserService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(userService.findAll());
    }

    @GetMapping("/{uuid}")
    public String findById(@PathVariable Long uuid) {
        return serialize(userService.findById(uuid));
    }

    @PostMapping
    public void save(@RequestBody String userDTO) {
        try {
            userService.save(deserialize(userDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody String updateDTO) {
        try {
            userService.update(id, deserialize(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private UserDTOToEntity deserialize(String userDTO) throws IOException {
        return objectMapper.readValue(userDTO, UserDTOToEntity.class);
    }
}



