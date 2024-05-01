package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.mapper.RequestStatusMapper;
import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.requeststatus.RequestStatusDTOToEntity;
import senla.enums.StatusRequest;
import senla.entities.RequestStatus;
import senla.repository.api.RequestStatusRepository;
import senla.servise.RequestStatusService;

import java.util.List;
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class RequestStatusServiceImpl implements RequestStatusService {
    private final RequestStatusRepository requestStatusRepository;
    @Transactional
    @Override
    public List<RequestStatusDTO> findAll() {
        return requestStatusRepository.findAll().stream()
                .map(RequestStatusMapper::convertToDto)
                .toList();
    }
    @Transactional
    @Override
    public RequestStatusDTO findById(long uuid) {
        RequestStatus requestStatus = requestStatusRepository.findById(uuid);
        return requestStatus != null ? RequestStatusMapper.convertToDto(requestStatus) : null;
    }
    @Transactional
    @Override
    public boolean save(RequestStatusDTOToEntity object) {
        RequestStatus requestStatus = RequestStatusMapper.createRequestStatusDto(object);
        requestStatusRepository.save(requestStatus);
        return requestStatus.getId() != null;
    }
    @Transactional
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
    @Transactional
    @Override
    public void deleteById(long uuid) {
        requestStatusRepository.deleteById(uuid);
    }
}

