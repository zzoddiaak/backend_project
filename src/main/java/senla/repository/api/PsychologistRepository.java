package senla.repository.api;

import senla.entities.Psychologist;

import java.math.BigDecimal;
import java.util.List;

public interface PsychologistRepository {
    Psychologist findById(Long uuid);
    List<Psychologist> findAll();
    void save(Psychologist psychologist);
    void deleteById(Long uuid);
    public List<Psychologist> findAllWithFetch(int experience);
    public List<Psychologist> findAllWithJoinFetch(BigDecimal hourlyRate);
    public List<Psychologist> findAllWithDetails();
}
