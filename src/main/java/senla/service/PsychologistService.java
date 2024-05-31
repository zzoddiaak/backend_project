package senla.service;

import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.dto.psychologist.PsychologistShortDTO;

import java.util.List;

public interface PsychologistService {
    List<PsychologistShortDTO> findAll();
    PsychologistShortDTO findById(long uuid);
    boolean save(PsychologistDTOToEntity object);
    boolean update(long uuid, PsychologistDTOToEntity updateDTO);
    void deleteById(long uuid);

}

