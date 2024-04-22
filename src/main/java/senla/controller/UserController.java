package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.user.UserDTOToEntity;
import senla.entities.User;
import senla.repository.api.UserRepository;

import java.io.IOException;
import java.util.List;

import static senla.entities.User_.email;
import static senla.entities.User_.secondname;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        List<User> users = userRepository.findAll();
        return serialize(users);
    }

    @GetMapping("/findAllWithDetails")
    public String findAllWithDetails() {
        List<User> users = userRepository.findAllWithDetails();
        return serialize(users);
    }

    @GetMapping("/findAllWithFetch")
    public String findAllWithFetch() {
        List<User> users = userRepository.findAllWithFetch(secondname.getName());
        return serialize(users);
    }

    @GetMapping("/findAllWithJoinFetch")
    public String findAllWithJoinFetch() {
        List<User> users = userRepository.findAllWithJoinFetch(email.getName());
        return serialize(users);
    }

    @GetMapping("/{uuid}")
    public String findById(@PathVariable Long uuid) {
        User user = userRepository.findById(uuid);
        return serialize(user);
    }

    /*@PostMapping
    public void save(@RequestBody String userDTO) {
        try {
            userRepository.save(deserialize(userDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody String updateDTO) {
        try {
            userRepository.update(id, deserialize(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
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
