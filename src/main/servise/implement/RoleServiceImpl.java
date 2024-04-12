package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.mapper.RoleMapper;
import senla.dto.role.RoleDTO;
import senla.dto.role.RoleDTOToEntity;
import senla.entities.Role;
import senla.enums.RoleName;
import senla.repository.iface.RoleRepository;
import senla.servise.RoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::convertToDto)
                .toList();
    }

    @Override
    public RoleDTO findById(long uuid) {
        Role role = roleRepository.findById(uuid);
        return role != null ? RoleMapper.convertToDto(role) : null;
    }

    @Override
    public boolean save(RoleDTOToEntity object) {
        RoleName roleName = object.getRoleName();
        if (roleName != null) {
            Role role = Role.builder().roleName(roleName).build();
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(long uuid, RoleDTOToEntity updateDTO) {
        Role role = roleRepository.findById(uuid);
        if (role != null) {
            RoleName roleName = updateDTO.getRoleName();
            if (roleName != null) {
                role.setRoleName(roleName);
            }
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(long uuid) {
        roleRepository.deleteById(uuid);
    }
}




