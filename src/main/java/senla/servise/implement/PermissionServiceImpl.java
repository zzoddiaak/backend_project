package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dto.permission.PermissionDTO;
import senla.dto.permission.PermissionDTOToEntity;
import senla.entities.Permission;
import senla.enums.PermissionType;
import senla.mapper.PermissionMapper;
import senla.repository.api.PermissionRepository;
import senla.servise.PermissionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    @Transactional
    @Override
    public List<PermissionDTO> findAll() {
        return permissionRepository.findAll().stream()
                .map(PermissionMapper::convertToDto)
                .toList();
    }
    @Transactional
    @Override
    public PermissionDTO findById(long uuid) {
        Permission permission = permissionRepository.findById(uuid);
        return permission != null ? PermissionMapper.convertToDto(permission) : null;
    }
    @Transactional
    @Override
    public boolean save(PermissionDTOToEntity object) {
        Permission permission = PermissionMapper.createPermissionDto(object);
        permissionRepository.save(permission);
        return permission.getId() != null;
    }
    @Transactional
    @Override
    public boolean update(long uuid, PermissionDTOToEntity updateDTO) {
        Permission permission = permissionRepository.findById(uuid);
        if (permission != null) {
            if (updateDTO.getPermissionType() != null) {
                permission.setPermissionType(PermissionType.valueOf(String.valueOf(updateDTO.getPermissionType())));
            }
            permissionRepository.save(permission);
            return true;
        }
        return false;
    }
    @Transactional
    @Override
    public void deleteById(long uuid) {
        permissionRepository.deleteById(uuid);
    }
}

