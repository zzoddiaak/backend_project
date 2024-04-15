package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.course.CourseDTOToEntity;
import senla.dto.course.CourseShortInfoDTO;
import senla.mapper.CourseMapper;
import senla.entities.Course;
import senla.repository.iface.CourseRepository;
import senla.servise.CourseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public List<CourseShortInfoDTO> findAll() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::convertToShortDto)
                .toList();
    }

    @Override
    public CourseShortInfoDTO findById(long uuid) {
        Course course = courseRepository.findById(uuid);
        return course != null ? CourseMapper.convertToShortDto(course) : null;
    }

    @Override
    public boolean save(CourseDTOToEntity object) {
        Course course = CourseMapper.createCourseDto(object);
        courseRepository.save(course);
        return course.getId() != null;
    }

    @Override
    public boolean update(long uuid, CourseDTOToEntity updateDTO) {
        Course course = courseRepository.findById(uuid);
        if (course != null) {
            if (!updateDTO.getCourseName().isEmpty()) course.setCourseName(updateDTO.getCourseName());
            if (updateDTO.getCoursePrice() != null) course.setCoursePrice(updateDTO.getCoursePrice());
            courseRepository.save(course);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(long uuid){
        courseRepository.deleteById(uuid);
    }
}