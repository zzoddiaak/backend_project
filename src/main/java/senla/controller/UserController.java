package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.user.UserDTOToEntity;
import senla.servise.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((userService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(userService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String userDTO){
        userService.save(objectMapper.readValue(userDTO, UserDTOToEntity.class));
    }
    @PutMapping("/id")
    @SneakyThrows
    public void update(@PathVariable long uuid, @RequestBody String userUpdateDTO){
        userService.update(uuid, objectMapper.readValue(userUpdateDTO, UserDTOToEntity.class));
    }
    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){userService.deleteById(uuid);}
}
