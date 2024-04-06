package senla.servise;



import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.requeststatus.RequestStatusDTOToEntity;

import java.util.List;

public interface RequestStatusService {
    List<RequestStatusDTO> findAll();
    RequestStatusDTO findById(long uuid);
    void save(RequestStatusDTOToEntity object);
    void deleteById(long uuid);
}