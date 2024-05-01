package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.mapper.RoleMapper;
import senla.dto.role.RoleDTO;
import senla.dto.role.RoleDTOToEntity;
import senla.entities.Role;
import senla.enums.RoleName;
import senla.repository.api.RoleRepository;
import senla.servise.RoleService;

import java.util.List;
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Transactional
    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::convertToDto)
                .toList();
    }
    @Transactional
    @Override
    public RoleDTO findById(long uuid) {
        Role role = roleRepository.findById(uuid);
        return role != null ? RoleMapper.convertToDto(role) : null;
    }
    @Transactional
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
    @Transactional
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
    @Transactional
    @Override
    public void deleteById(long uuid) {
        roleRepository.deleteById(uuid);
    }
}




