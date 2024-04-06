package senla.dto.mapper;

import senla.dto.role.RoleDTO;
import senla.dto.role.RoleDTOToEntity;
import senla.entities.Role;

public class RoleMapper {
    public static RoleDTO convertToDTO(Role source){
        return source == null ? null : RoleDTO.builder()
                .roleName(source.getRoleName())
                .build();


    }
    public static Role convertDTOToEntity(RoleDTOToEntity source){
        return source == null ? null : Role.builder()
                .roleName(source.getRoleName())
                .build();

    }
}
