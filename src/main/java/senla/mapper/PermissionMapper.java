package senla.mapper;

import senla.dto.permission.PermissionDTO;
import senla.dto.permission.PermissionDTOToEntity;
import senla.entities.Permission;

public class PermissionMapper {
    public static PermissionDTO convertToDto(Permission source){
        return source == null ? null : PermissionDTO.builder()
                .permissionType(source.getPermissionType().name())
                .build();

    }

    public static Permission createPermissionDto(PermissionDTOToEntity source){
        return source == null ? null : Permission.builder()
                .permissionType(Permission.PermissionType.valueOf(source.getPermissionType()))
                .build();

    }
}
