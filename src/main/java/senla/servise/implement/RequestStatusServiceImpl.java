package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.mapper.RequestStatusMapper;
import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.requeststatus.RequestStatusDTOToEntity;
import senla.enums.StatusRequest;
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
        RequestStatus requestStatus = requestStatusRepository.findById(uuid);
        return requestStatus != null ? RequestStatusMapper.convertToDto(requestStatus) : null;
    }

    @Override
    public boolean save(RequestStatusDTOToEntity object) {
        RequestStatus requestStatus = RequestStatusMapper.createRequestStatusDto(object);
        requestStatusRepository.save(requestStatus);
        return requestStatus.getId() != null;
    }

    @Override
    public boolean update(long uuid, RequestStatusDTOToEntity updateDTO) {
        RequestStatus requestStatus = requestStatusRepository.findById(uuid);
        if (requestStatus != null) {
            if (updateDTO.getRequestStatus() != null) {
                requestStatus.setRequestStatus(StatusRequest.valueOf(String.valueOf(updateDTO.getRequestStatus())));
            }
            requestStatusRepository.save(requestStatus);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(long uuid) {
        requestStatusRepository.deleteById(uuid);
    }
}

