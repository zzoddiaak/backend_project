package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.role.RoleDTOToEntity;
import senla.servise.RoleService;

@RestController
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((roleService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(roleService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String roleDTO){
        roleService.save(objectMapper.readValue(roleDTO, RoleDTOToEntity.class));
    }
    @PutMapping("/id")
    @SneakyThrows
    public void update(@PathVariable long uuid, @RequestBody String updateDTO){
        roleService.update(uuid, objectMapper.readValue(updateDTO, RoleDTOToEntity.class));
    }

    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){roleService.deleteById(uuid);}
}
