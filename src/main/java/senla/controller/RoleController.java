package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.role.RoleDTOToEntity;
import senla.service.RoleService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService roleService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(roleService.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
        return serialize(roleService.findById(id));
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
    public void update(@PathVariable("id") long id, @RequestBody String updateDTO) {
        try {
            roleService.update(id, deserialize(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
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
