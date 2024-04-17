package senla.servise;

import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.requeststatus.RequestStatusDTOToEntity;

import java.util.List;

public interface RequestStatusService {
    List<RequestStatusDTO> findAll();
    RequestStatusDTO findById(long uuid);
    boolean save(RequestStatusDTOToEntity object);
    boolean update(long uuid, RequestStatusDTOToEntity updateDTO);
    void deleteById(long uuid);

}

