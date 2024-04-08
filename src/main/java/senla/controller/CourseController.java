package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.course.CourseDTOToEntity;
import senla.servise.CourseService;

@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((courseService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(courseService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String courseDTO){
        courseService.save(objectMapper.readValue(courseDTO, CourseDTOToEntity.class));
    }
    @PutMapping("/id")
    @SneakyThrows
    public void update(@PathVariable long uuid, @RequestBody String updateDTO){
        courseService.update(uuid, objectMapper.readValue(updateDTO, CourseDTOToEntity.class));
    }
    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){courseService.deleteById(uuid);}
}
