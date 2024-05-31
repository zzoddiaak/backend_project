package senla.service;

import senla.dto.permission.PermissionDTO;
import senla.dto.permission.PermissionDTOToEntity;

import java.util.List;

public interface PermissionService {
    List<PermissionDTO> findAll();
    PermissionDTO findById(long uuid);
    boolean save(PermissionDTOToEntity object);
    boolean update(long uuid, PermissionDTOToEntity updateDTO);
    void deleteById(long uuid);
}

