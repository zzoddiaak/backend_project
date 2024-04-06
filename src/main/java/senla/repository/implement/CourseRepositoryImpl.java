package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.Course;
import senla.repository.AbstractRepository;
import senla.repository.iface.CourseRepository;

import java.math.BigDecimal;

@Repository

public class CourseRepositoryImpl extends AbstractRepository<Course> implements CourseRepository {
    public CourseRepositoryImpl(){
        save(Course.builder()
                .courseName("Лечение")
                .coursePrice(new BigDecimal(23.4))
                .build());
    }
}
