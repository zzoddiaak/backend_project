package senla.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.mapper.PsychologistMapper;
import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.dto.psychologist.PsychologistShortDTO;
import senla.entities.Psychologist;
import senla.repository.api.PsychologistRepository;
import senla.service.PsychologistService;

import java.util.List;
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PsychologistServiceImpl implements PsychologistService {

    private final PsychologistRepository psychologistRepository;

    @Override
    public List<PsychologistShortDTO> findAll(){
        return psychologistRepository.findAll().stream()
                .map(PsychologistMapper::convertToShortDto)
                .toList();

    }

    @Override
    public PsychologistShortDTO findById(long uuid){
        Psychologist psychologist = psychologistRepository.findById(uuid);
        return psychologist != null ? PsychologistMapper.convertToShortDto(psychologist) : null;
    }

    @Override
    public boolean save(PsychologistDTOToEntity object){
        Psychologist psychologist = PsychologistMapper.createPsychologistDto(object);
        psychologistRepository.save(psychologist);
        return psychologist.getId() != null;
    }

    @Override
    public boolean update(long uuid, PsychologistDTOToEntity updateDTO){
        Psychologist psychologist = psychologistRepository.findById(uuid);
        if (psychologist != null) {
            if (updateDTO.getExperience() != null) psychologist.setExperience(updateDTO.getExperience());
            if (updateDTO.getHourlyRate() != null) psychologist.setHourlyRate(updateDTO.getHourlyRate());
            if (updateDTO.getRatingValue() != null) psychologist.setRatingValue(updateDTO.getRatingValue());
            psychologistRepository.save(psychologist);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(long uuid){
        psychologistRepository.deleteById(uuid);
    }

}

