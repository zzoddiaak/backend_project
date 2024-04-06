package senla.dto.mapper;


import senla.dto.permission.PermissionDTO;
import senla.dto.permission.PermissionDTOToEntity;
import senla.entities.Permission;

public class PermissionMapper {
    public static PermissionDTO convertEntityToDTO(Permission source){
        return source == null ? null : PermissionDTO.builder()
                .permissionName(source.getPermissionName())
                .build();

    }
    public static Permission convertDTOToEntity(PermissionDTOToEntity source){
        return source == null ? null : Permission.builder()
                .permissionName(source.getPermissionName())
                .build();

    }

}
