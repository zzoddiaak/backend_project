package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.mapper.RequestStatusMapper;
import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.requeststatus.RequestStatusDTOToEntity;
import senla.repository.iface.RequestStatusRepository;
import senla.servise.RequestStatusService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RequestStatusServiceImpl implements RequestStatusService {
    private final RequestStatusRepository requestStatusRepository;
    @Override
    public List<RequestStatusDTO> findAll(){
        return requestStatusRepository.findAll().stream()
                .map(RequestStatusMapper::convertToDTO)
                .toList();

    }
    @Override
    public RequestStatusDTO findById(long uuid){
        return RequestStatusMapper.convertToDTO(requestStatusRepository.findById(uuid));
    }
    @Override
    public void save(RequestStatusDTOToEntity object){
        requestStatusRepository.save(RequestStatusMapper.convertDTOToEntity(object));
    }
    @Override
    public void deleteById(long uuid){
        requestStatusRepository.deleteById(uuid);
    }
}
