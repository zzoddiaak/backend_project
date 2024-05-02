package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.permission.PermissionDTOToEntity;
import senla.servise.PermissionService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/permissions")
public class PermissionController {
    private final PermissionService permissionService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(permissionService.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
        return serialize(permissionService.findById(id));
    }

    @PostMapping
    public void save(@RequestBody String permissionDTO) {
        try {
            permissionService.save(deserialize(permissionDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody String updateDTO) {
        try {
            permissionService.update(id, deserialize(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        permissionService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PermissionDTOToEntity deserialize(String permissionDTO) throws IOException {
        return objectMapper.readValue(permissionDTO, PermissionDTOToEntity.class);
    }
}
