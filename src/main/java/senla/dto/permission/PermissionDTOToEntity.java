package senla.dto.permission;

import lombok.*;
import senla.enums.PermissionType;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTOToEntity {
    private PermissionType permissionType;
}
