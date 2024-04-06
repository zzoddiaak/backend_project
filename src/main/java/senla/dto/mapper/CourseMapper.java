package senla.dto.mapper;

import senla.dto.client.ClientFullInfoDTO;
import senla.dto.course.CourseDTOToEntity;
import senla.dto.course.CourseFullInfoDTO;
import senla.dto.course.CourseShortInfoDTO;
import senla.entities.Course;

public class CourseMapper {
    public static CourseFullInfoDTO convertEntityToFullDTO(Course source){
        return source == null ? null : CourseFullInfoDTO.builder()
                .uuid(source.getUuid())
                .order(OrderMapper.convertToFullDTO(source.getOrder()))
                .coursePrice(source.getCoursePrice())
                .courseName(source.getCourseName())
                .build();

    }
    public static CourseShortInfoDTO convertEntityToShortDTO(Course source){
        return source == null ? null : CourseShortInfoDTO.builder()
                .courseName(source.getCourseName())
                .coursePrice(source.getCoursePrice())
                .order(OrderMapper.convertToShortDTO( source.getOrder()))
                .build();

    }
    public static Course convertDTOToEntity(CourseDTOToEntity source){
        return source == null ? null : Course.builder()
                .courseName(source.getCourseName())
                .coursePrice(source.getCoursePrice())
                .order(OrderMapper.convertDTOToEntity(source.getOrder()))
                .build();

    }
}
