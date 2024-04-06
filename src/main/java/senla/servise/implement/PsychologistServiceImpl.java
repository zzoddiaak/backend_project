package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import senla.dto.mapper.PsychologistMapper;
import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.dto.psychologist.PsychologistShortDTO;
import senla.repository.iface.PsychologistRepository;
import senla.servise.PsychologistService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PsychologistServiceImpl implements PsychologistService {

    private final PsychologistRepository psychologistRepository;
    @Override
    public List<PsychologistShortDTO> findAll(){
        return psychologistRepository.findAll().stream()
                .map(PsychologistMapper::convertToShortDTO)
                .toList();

    }
    @Override
    public PsychologistShortDTO findById(long uuid){
        return PsychologistMapper.convertToShortDTO(psychologistRepository.findById(uuid));
    }
    @Override
    public void save(PsychologistDTOToEntity object){
        psychologistRepository.save(PsychologistMapper.convertDTOToEntity(object));
    }
    @Override
    public void deleteById(long uuid){
        psychologistRepository.deleteById(uuid);
    }
}