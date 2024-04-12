package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.permission.PermissionDTO;
import senla.dto.permission.PermissionDTOToEntity;
import senla.entities.Permission;
import senla.enums.PermissionType;
import senla.mapper.PermissionMapper;
import senla.repository.iface.PermissionRepository;
import senla.servise.PermissionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public List<PermissionDTO> findAll() {
        return permissionRepository.findAll().stream()
                .map(PermissionMapper::convertToDto)
                .toList();
    }

    @Override
    public PermissionDTO findById(long uuid) {
        Permission permission = permissionRepository.findById(uuid);
        return permission != null ? PermissionMapper.convertToDto(permission) : null;
    }

    @Override
    public boolean save(PermissionDTOToEntity object) {
        Permission permission = PermissionMapper.createPermissionDto(object);
        permissionRepository.save(permission);
        return permission.getId() != null;
    }

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

    @Override
    public void deleteById(long uuid) {
        permissionRepository.deleteById(uuid);
    }
}

