package senla.repository.implement;

import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.config.HibernateConfig;
import senla.config.LiquibaseConfig;
import senla.entities.Psychologist;
import senla.entities.Role;
import senla.entities.User;
import senla.enums.RoleName;
import senla.repository.api.PsychologistRepository;
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
public class PsychologistRepositoryImplTest {
    @Resource
    private PsychologistRepository psychologistRepository;

    @Test
    public void findAllWithFetch() {
        Psychologist psychologistser = Psychologist.builder()
                .experience(7)
                .build();

        psychologistRepository.save(psychologistser);

        List<Psychologist> psychologistssers = psychologistRepository.findAllWithFetch(psychologistser.getExperience());

        assertNotNull(psychologistssers);
        assertFalse(psychologistssers.isEmpty());

        psychologistssers.forEach(x -> assertEquals(psychologistser.getExperience(), x.getExperience()));
    }

    @Test
    public void findAllWithJoinFetch() {
        Psychologist psychologist = Psychologist.builder()
                .hourlyRate(BigDecimal.valueOf(50.0))
                .build();

        psychologistRepository.save(psychologist);

        List<Psychologist> psychologists = psychologistRepository.findAllWithJoinFetch(psychologist.getHourlyRate());

        assertNotNull(psychologists);
        assertFalse(psychologists.isEmpty());
    }

    @Test
    public void findAllWithDetails() {
        Psychologist psychologist = Psychologist.builder()

                .build();

        psychologistRepository.save(psychologist);

        List<Psychologist> psychologists = psychologistRepository.findAllWithDetails();

        assertNotNull(psychologists);
        assertFalse(psychologists.isEmpty());
    }
}