package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.Psychologist;
import senla.entities.User;
import senla.repository.AbstractRepository;
import senla.repository.iface.PsychologistRepository;
import senla.repository.iface.UserRepository;

import java.math.BigDecimal;
@Repository

public class PsychologistRepositoryImpl extends AbstractRepository<Psychologist> implements PsychologistRepository {

    public PsychologistRepositoryImpl(){

        save(Psychologist.builder()
                .experience(32)
                .hourlyRate(new BigDecimal("32.3"))
                .ratingValue(345)
                .build());
        save(Psychologist.builder()
                .experience(62)
                .hourlyRate(new BigDecimal("12.3"))
                .ratingValue(87)
                .build());
    }
}
