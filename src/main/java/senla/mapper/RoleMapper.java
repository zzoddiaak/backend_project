package senla.mapper;

import senla.dto.role.RoleDTO;
import senla.dto.role.RoleDTOToEntity;
import senla.entities.Role;

public class RoleMapper {
    public static RoleDTO convertToDto(Role source){
        return source == null ? null : RoleDTO.builder()
                .roleName(source.getRoleName().name())
                .build();
    }

    public static Role createRoleDto(RoleDTOToEntity source){
        return source == null ? null : Role.builder()
                .roleName(Role.RoleName.valueOf(source.getRoleName()))
                .build();
    }
}
