package senla.repository.api;

import senla.entities.Course;
import senla.entities.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;

public interface CourseRepository {
    Course findById(Long uuid);
    List<Course> findAll();
    void save(Course course);
    void deleteById(Long uuid);
    public List<Course> findAllWithFetch(BigDecimal coursePrice);
    public List<Course> findAllWithJoinFetch(String courseName);
    public List<Course> findAllWithDetails();
}
