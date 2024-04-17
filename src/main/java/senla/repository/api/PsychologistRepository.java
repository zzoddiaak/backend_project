package senla.repository.api;

import senla.entities.Psychologist;

import java.util.List;

public interface PsychologistRepository {
    Psychologist findById(Long uuid);
    List<Psychologist> findAll();
    void save(Psychologist psychologist);
    void deleteById(Long uuid);
}
