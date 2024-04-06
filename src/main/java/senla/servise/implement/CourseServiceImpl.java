package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.course.CourseDTOToEntity;
import senla.dto.course.CourseShortInfoDTO;
import senla.dto.mapper.CourseMapper;
import senla.entities.Course;
import senla.repository.iface.CourseRepository;
import senla.servise.CourseService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    @Override
    public List<CourseShortInfoDTO> findAll(){
        return courseRepository.findAll().stream()
                .map(CourseMapper::convertEntityToShortDTO)
                .toList();

    }
    @Override
    public CourseShortInfoDTO findById(long uuid){
        return CourseMapper.convertEntityToShortDTO(courseRepository.findById(uuid));
    }
    @Override
    public void save(CourseDTOToEntity object){
        courseRepository.save(CourseMapper.convertDTOToEntity(object));
    }
    @Override
    public void update(long uuid, CourseDTOToEntity updateDTO){
            Course course = courseRepository.findById(uuid);
        if (!updateDTO.getCourseName().isEmpty()) course.setCourseName(updateDTO.getCourseName());
        if (updateDTO.getCoursePrice() != null) course.setCoursePrice(updateDTO.getCoursePrice());
    }
    @Override
    public void deleteById(long uuid){
        courseRepository.deleteById(uuid);
    }

}
