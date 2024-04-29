package senla.repository.implement;

import jakarta.annotation.Resource;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.config.HibernateConfig;
import senla.config.LiquibaseConfig;
import senla.entities.Course;
import senla.entities.Role;
import senla.entities.User;
import senla.enums.RoleName;
import senla.repository.api.CourseRepository;
import senla.repository.api.OrderRepository;
import senla.repository.api.UserRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class CourseRepositoryImplTest {
    @Resource
    private CourseRepository courseRepository;

    @Test
    public void findAllWithFetch() {
        Course course = Course.builder()
                .coursePrice(BigDecimal.valueOf(5.5))
                .build();

        courseRepository.save(course);

        List<Course> courses = courseRepository.findAllWithFetch(course.getCoursePrice());

        assertNotNull(courses);
        assertFalse(courses.isEmpty());

        courses.forEach(x -> assertEquals(course.getCoursePrice(), x.getCoursePrice()));
    }

    @Test
    public void findAllWithJoinFetch() {
        Course course = Course.builder()
                .courseName("Test")
                .build();

        courseRepository.save(course);

        List<Course> courses = courseRepository.findAllWithJoinFetch(course.getCourseName());

        assertNotNull(courses);
        assertFalse(courses.isEmpty());
    }

    @Test
    public void findAllWithDetails() {
        Course course = Course.builder()
                .build();
        courseRepository.save(course);

        List<Course> courses = courseRepository.findAllWithDetails();

        assertNotNull(courses);
        assertFalse(courses.isEmpty());
    }
}