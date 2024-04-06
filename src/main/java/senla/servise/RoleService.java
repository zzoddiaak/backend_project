package senla.servise;

import senla.dto.role.RoleDTO;
import senla.dto.role.RoleDTOToEntity;

import java.util.List;

public interface RoleService {
    List<RoleDTO> findAll();
    RoleDTO findById(long uuid);
    void save(RoleDTOToEntity object);
    void deleteById(long uuid);
}
