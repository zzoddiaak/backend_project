package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.user.UserDTOToEntity;
import senla.service.UserService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(userService.findAll());
    }



    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
        return serialize(userService.findById(id));
    }


    @PostMapping
    public void save(@RequestBody String userShortInfoDTO) {
        try {
            userService.save(deserialize(userShortInfoDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody String updateDTO) {
        try {
            userService.update(id, deserialize(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
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
