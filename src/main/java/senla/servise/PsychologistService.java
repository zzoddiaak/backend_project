package senla.servise;

import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.dto.psychologist.PsychologistShortDTO;

import java.util.List;

public interface PsychologistService {
    List<PsychologistShortDTO> findAll();
    PsychologistShortDTO findById(long uuid);
    void save(PsychologistDTOToEntity object);
    void deleteById(long uuid);
}
