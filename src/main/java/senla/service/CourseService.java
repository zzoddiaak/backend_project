package senla.service;

import senla.dto.course.CourseDTOToEntity;
import senla.dto.course.CourseShortInfoDTO;

import java.util.List;

public interface CourseService {
    List<CourseShortInfoDTO> findAll();
    CourseShortInfoDTO findById(long uuid);
    boolean save(CourseDTOToEntity object);
    boolean update(long uuid, CourseDTOToEntity updateDTO);
    void deleteById(long uuid);

}

