package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.requeststatus.RequestStatusDTOToEntity;
import senla.enums.StatusRequest;
import senla.mapper.RequestStatusMapper;
import senla.entities.RequestStatus;
import senla.repository.iface.RequestStatusRepository;
import senla.servise.RequestStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestStatusServiceImpl implements RequestStatusService {
    private final RequestStatusRepository requestStatusRepository;

    @Override
    public List<RequestStatusDTO> findAll() {
        return requestStatusRepository.findAll().stream()
                .map(RequestStatusMapper::convertToDto)
                .toList();
    }

    @Override
    public RequestStatusDTO findById(long uuid) {
        return RequestStatusMapper.convertToDto(requestStatusRepository.findById(uuid));
    }

    @Override
    public void save(RequestStatusDTOToEntity object) {
        requestStatusRepository.save(RequestStatusMapper.createRequestStatusDto(object));
    }

    @Override
    public void update(long uuid, RequestStatusDTOToEntity updateDTO) {
        RequestStatus requestStatus = requestStatusRepository.findById(uuid);
        if (updateDTO.getRequestStatus() != null) {
            requestStatus.setRequestStatus(StatusRequest.valueOf(String.valueOf(updateDTO.getRequestStatus())));
        }
        requestStatusRepository.save(requestStatus);
    }

    @Override
    public void deleteById(long uuid) {
        requestStatusRepository.deleteById(uuid);
    }
}
