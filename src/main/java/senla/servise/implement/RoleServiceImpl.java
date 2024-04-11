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
        return RoleMapper.convertToDto(roleRepository.findById(uuid));
    }

    @Override
    public void save(RoleDTOToEntity object) {
        Role role = Role.builder()
                .roleName(RoleName.valueOf(String.valueOf(object.getRoleName())))
                .build();
        roleRepository.save(role);
    }

    @Override
    public void update(long uuid, RoleDTOToEntity updateDTO) {
        Role role = roleRepository.findById(uuid);
        if (updateDTO.getRoleName() != null) {
            role.setRoleName(RoleName.valueOf(String.valueOf(updateDTO.getRoleName())));
        }
        roleRepository.save(role);
    }

    @Override
    public void deleteById(long uuid) {
        roleRepository.deleteById(uuid);
    }
}
