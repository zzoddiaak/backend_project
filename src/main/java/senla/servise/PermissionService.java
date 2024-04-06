package senla.servise;



import senla.dto.permission.PermissionDTO;
import senla.dto.permission.PermissionDTOToEntity;

import java.util.List;

public interface PermissionService {
    List<PermissionDTO> findAll();
    PermissionDTO findById(long uuid);
    void save(PermissionDTO object);
    void deleteById(long uuid);
}
