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
import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.dto.psychologist.PsychologistShortDTO;
import senla.entities.Psychologist;
import senla.mapper.PsychologistMapper;
import senla.repository.api.PsychologistRepository;
import senla.service.impl.PsychologistServiceImpl;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class PsychologistServiceTest{
    @InjectMocks
    private PsychologistServiceImpl psychologistService;

    @Spy
    private PsychologistRepository psychologistRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        Psychologist psychologist = Psychologist.builder()
                .id(1L)
                .experience(1)
                .build();

        Psychologist psychologist1 = Psychologist.builder()
                .id(1L)
                .experience(2)
                .build();

        Mockito.when(psychologistRepository.findAll()).thenReturn(List.of(psychologist, psychologist1));

        List<PsychologistShortDTO> actual = psychologistService.findAll();

        Mockito.verify(psychologistRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        Psychologist psychologist = Psychologist.builder()
                .id(1L)
                .experience(1)
                .build();

        Mockito.when(psychologistRepository.findById(any(Long.class))).thenReturn(psychologist);

        PsychologistShortDTO expected = PsychologistMapper.convertToShortDto(psychologist);
        PsychologistShortDTO actual = psychologistService.findById(psychologist.getId());

        Mockito.verify(psychologistRepository, Mockito.times(1)).findById(any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getExperience(), actual.getExperience());
    }

    @Test
    public void saveTest(){
        PsychologistDTOToEntity dto = PsychologistDTOToEntity.builder()
                .experience(1)
                .build();

        Psychologist psychologist = PsychologistMapper.createPsychologistDto(dto);

        Mockito.doNothing().when(psychologistRepository).save(any(Psychologist.class));

        psychologistService. save(dto);

        Mockito.verify(psychologistRepository, Mockito.times(1)).save(any(Psychologist.class));
    }

    @Test
    public void updateTest(){
        Psychologist psychologist = Psychologist.builder()
                .id(1L)
                .experience(2)

                .build();

        PsychologistDTOToEntity dto = new PsychologistDTOToEntity();
        dto.setExperience(1);

        Mockito.when(psychologistRepository.findById(psychologist.getId())).thenReturn(psychologist);

        psychologistService.update(psychologist.getId(), dto);

        Mockito.verify(psychologistRepository, Mockito.times(1)).save(any(Psychologist.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        psychologistService.deleteById(id);
        Mockito.verify(psychologistRepository, Mockito.times(1)).deleteById(id);
    }
}
