package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.course.CourseDTOToEntity;
import senla.servise.CourseService;
@RequestMapping("/api/v1/courses")
@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(courseService.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id) {
        return serialize(courseService.findById(id));
    }

    @PostMapping
    public void save(@RequestBody String courseDTO) {
        try {
            courseService.save(deserialize(courseDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody String updateDTO) {
        try {
            courseService.update(id, deserialize(updateDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private CourseDTOToEntity deserialize(String courseDTO) {
        try {
            return objectMapper.readValue(courseDTO, CourseDTOToEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
