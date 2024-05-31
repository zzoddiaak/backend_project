package senla.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.config.HibernateConfig;
import senla.config.LiquibaseConfig;
import senla.dto.course.CourseDTOToEntity;
import senla.dto.course.CourseShortInfoDTO;
import senla.entities.Course;
import senla.mapper.CourseMapper;
import senla.repository.api.CourseRepository;
import senla.service.impl.CourseServiceImpl;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class CourseServiceTest{
    @InjectMocks
    private CourseServiceImpl courseService;

    @Spy
    private CourseRepository courseRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        Course course = Course.builder()
                .id(1L)
                .courseName("TESRT")

                .build();

        Course course1 = Course.builder()
                .id(1L)
                .courseName("TEST")

                .build();

        Mockito.when(courseRepository.findAll()).thenReturn(List.of(course, course1));

        List<CourseShortInfoDTO> actual = courseService.findAll();

        Mockito.verify(courseRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        Course course = Course.builder()
                .id(1L)
                .courseName("TEST")

                .build();

        Mockito.when(courseRepository.findById(any(Long.class))).thenReturn(course);

        CourseShortInfoDTO expected = CourseMapper.convertToShortDto(course);
        CourseShortInfoDTO actual = courseService.findById(course.getId());

        Mockito.verify(courseRepository, Mockito.times(1)).findById(Mockito.any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getCourseName(), actual.getCourseName());
    }

    @Test
    public void saveTest(){
        CourseDTOToEntity dto = CourseDTOToEntity.builder()

                .build();

        Course course = CourseMapper.createCourseDto(dto);

        Mockito.doNothing().when(courseRepository).save(Mockito.any(Course.class));

        courseService.save(dto);

        Mockito.verify(courseRepository, Mockito.times(1)).save(Mockito.any(Course.class));
    }

    @Test
    public void updateTest(){
        Course course = Course.builder()
                .id(1L)
                .courseName("TEST")
                .build();

        CourseDTOToEntity dto = new CourseDTOToEntity();
        dto.setCourseName("TESTT");

        Mockito.when(courseRepository.findById(course.getId())).thenReturn(course);

        courseService.update(course.getId(), dto);

        Mockito.verify(courseRepository, Mockito.times(1)).save(Mockito.any(Course.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        courseService.deleteById(id);
        Mockito.verify(courseRepository, Mockito.times(1)).deleteById(id);
    }
}
