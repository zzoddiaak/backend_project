package senla.servise.implement;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.mapper.PermissionMapper;
import senla.dto.permission.PermissionDTO;
import senla.dto.permission.PermissionDTOToEntity;
import senla.entities.Permission;
import senla.repository.iface.PermissionRepository;
import senla.servise.PermissionService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    @Override
    public List<PermissionDTO> findAll(){
        return permissionRepository.findAll().stream()
                .map(PermissionMapper::convertEntityToDTO)
                .toList();

    }
    @Override
    public PermissionDTO findById(long uuid){
        return PermissionMapper.convertEntityToDTO(permissionRepository.findById(uuid));
    }
    @Override
    public void save(PermissionDTOToEntity object){
        permissionRepository.save(PermissionMapper.convertDTOToEntity(object));
    }
    @Override
    public void update(long uuid, PermissionDTOToEntity updateDTO){
        Permission permission = permissionRepository.findById(uuid);
        if (!updateDTO.getPermissionName().isEmpty()) permission.setPermissionName(updateDTO.getPermissionName());
    }
    @Override
    public void deleteById(long uuid){
        permissionRepository.deleteById(uuid);
    }
}
