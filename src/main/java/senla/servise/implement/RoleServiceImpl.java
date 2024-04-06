package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.mapper.RoleMapper;
import senla.dto.role.RoleDTO;
import senla.dto.role.RoleDTOToEntity;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;
import senla.entities.Role;
import senla.entities.SessionRequest;
import senla.repository.iface.RoleRepository;
import senla.servise.RoleService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public List<RoleDTO> findAll(){
        return roleRepository.findAll().stream()
                .map(RoleMapper::convertToDTO)
                .toList();

    }
    @Override
    public RoleDTO findById(long uuid){
        return RoleMapper.convertToDTO(roleRepository.findById(uuid));
    }
    @Override
    public void save(RoleDTOToEntity object){
        roleRepository.save(RoleMapper.convertDTOToEntity(object));
    }
    @Override
    public void update(long uuid, RoleDTOToEntity updateDTO){
        Role role = roleRepository.findById(uuid);
        if (!updateDTO.getRoleName().isEmpty()) role.setRoleName(updateDTO.getRoleName());
    }
    @Override
    public void deleteById(long uuid){
        roleRepository.deleteById(uuid);
    }
}
