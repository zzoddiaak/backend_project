package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.permission.PermissionDTO;
import senla.dto.permission.PermissionDTOToEntity;

import senla.entities.Permission;
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
        return PermissionMapper.convertToDto(permissionRepository.findById(uuid));
    }

    @Override
    public void save(PermissionDTOToEntity object) {
        permissionRepository.save(PermissionMapper.createPermissionDto(object));
    }

    @Override
    public void update(long uuid, PermissionDTOToEntity updateDTO) {
        Permission permission = permissionRepository.findById(uuid);
        if (updateDTO.getPermissionType() != null) {
            permission.setPermissionType(Permission.PermissionType.valueOf(updateDTO.getPermissionType()));
        }
        permissionRepository.save(permission);
    }

    @Override
    public void deleteById(long uuid) {
        permissionRepository.deleteById(uuid);
    }
}
