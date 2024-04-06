package senla.servise;

import senla.dto.course.CourseDTOToEntity;
import senla.dto.course.CourseShortInfoDTO;

import java.util.List;

public interface CourseService {
    List<CourseShortInfoDTO> findAll();
    CourseShortInfoDTO findById(long uuid);
    void save(CourseDTOToEntity object);
    void deleteById(long uuid);
}
