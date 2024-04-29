package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.user.UserDTOToEntity;
import senla.entities.User;
import senla.repository.api.UserRepository;
import senla.servise.SessionRequestService;
import senla.servise.UserService;

import java.io.IOException;
import java.util.List;

import static senla.entities.User_.email;
import static senla.entities.User_.secondname;

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


  /*  @GetMapping("/findAllWithDetails")
    public String findAllWithDetails() {
        List<User> users = userService.findAllWithDetails();
        return serialize(users);
    }

    @GetMapping("/findAllWithFetch")
    public String findAllWithFetch() {
        List<User> users = userService.findAllWithFetch(secondname.getName());
        return serialize(users);
    }

    @GetMapping("/findAllWithJoinFetch")
    public String findAllWithJoinFetch() {
        List<User> users = userService.findAllWithJoinFetch(email.getName());
        return serialize(users);
    }*/

    @GetMapping("/{uuid}")
    public String findById(@PathVariable Long id) {
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
