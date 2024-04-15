package senla.repository.api;

import senla.entities.Course;

import java.util.List;

public interface CourseRepository {
    Course findById(Long uuid);
    List<Course> findAll();
    void save(Course course);
    void deleteById(Long uuid);
}
