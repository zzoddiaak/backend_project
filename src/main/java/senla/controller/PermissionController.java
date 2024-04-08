package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.permission.PermissionDTOToEntity;
import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.servise.PermissionService;
@RestController
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((permissionService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(permissionService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String permisssionDTO){
        permissionService.save(objectMapper.readValue(permisssionDTO, PermissionDTOToEntity.class));
    }
    @PutMapping("/id")
    @SneakyThrows
    public void update(@PathVariable long uuid, @RequestBody String updateDTO){
        permissionService.update(uuid, objectMapper.readValue(updateDTO, PermissionDTOToEntity.class));
    }
    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){permissionService.deleteById(uuid);}
}
