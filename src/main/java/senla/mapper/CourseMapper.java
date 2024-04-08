package senla.mapper;

import senla.dto.course.CourseDTOToEntity;
import senla.dto.course.CourseFullInfoDTO;
import senla.dto.course.CourseShortInfoDTO;
import senla.entities.Course;

public class CourseMapper {
    public static CourseFullInfoDTO convertToFullDto(Course source){
        return source == null ? null : CourseFullInfoDTO.builder()
                .order(OrderMapper.convertToFullDto(source.getOrder()))
                .coursePrice(source.getCoursePrice())
                .courseName(source.getCourseName())
                .build();

    }
    public static CourseShortInfoDTO convertToShortDto(Course source){
        return source == null ? null : CourseShortInfoDTO.builder()
                .courseName(source.getCourseName())
                .coursePrice(source.getCoursePrice())
                .order(OrderMapper.convertToShortDto( source.getOrder()))
                .build();

    }
    public static Course createCourseDto(CourseDTOToEntity source){
        return source == null ? null : Course.builder()
                .courseName(source.getCourseName())
                .coursePrice(source.getCoursePrice())
                .order(OrderMapper.createOrderDto(source.getOrder()))
                .build();

    }
}
