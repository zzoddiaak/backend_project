package senla.servise;

import senla.dto.role.RoleDTO;
import senla.dto.role.RoleDTOToEntity;

import java.util.List;

public interface RoleService {
    List<RoleDTO> findAll();
    RoleDTO findById(long uuid);
    boolean save(RoleDTOToEntity object);
    boolean update(long uuid, RoleDTOToEntity updateDTO);
    void deleteById(long uuid);
}

