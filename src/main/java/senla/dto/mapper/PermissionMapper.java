package senla.dto.mapper;


import senla.dto.permission.PermissionDTO;
import senla.entities.Permission;

public class PermissionMapper {
    public static PermissionDTO convertEntityToDTO(Permission source){
        return source == null ? null : PermissionDTO.builder()
                .permissionName(source.getPermissionName())
                .build();

    }
    public static Permission convertDTOToEntity(PermissionDTO source){
        return source == null ? null : Permission.builder()
                .permissionName(source.getPermissionName())
                .build();

    }

}
