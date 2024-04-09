package senla.mapper;

import senla.dto.permission.PermissionDTO;
import senla.dto.permission.PermissionDTOToEntity;
import senla.entities.Permission;
import senla.enums.PermissionType;

public class PermissionMapper {
    public static PermissionDTO convertToDto(Permission source){
        return source == null ? null : PermissionDTO.builder()
                .permissionType(source.getPermissionType())
                .build();

    }

    public static Permission createPermissionDto(PermissionDTOToEntity source) {
        return source == null ? null : Permission.builder()
                .permissionType(source.getPermissionType())
                .build();
    }


}
