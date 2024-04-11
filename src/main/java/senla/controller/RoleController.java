package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.role.RoleDTOToEntity;
import senla.servise.RoleService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(roleService.findAll());
    }

    @GetMapping("/{uuid}")
    public String findById(@PathVariable Long uuid) {
        return serialize(roleService.findById(uuid));
    }

    @PostMapping
    public void save(@RequestBody String roleDTO) {
        try {
            roleService.save(deserialize(roleDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody String updateDTO) {
        try {
            roleService.update(id, deserialize(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private RoleDTOToEntity deserialize(String roleDTO) throws IOException {
        return objectMapper.readValue(roleDTO, RoleDTOToEntity.class);
    }
}
