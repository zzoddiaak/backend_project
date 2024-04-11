package senla.entities;

import lombok.*;
import senla.enums.PermissionType;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends Entity {
    private PermissionType permissionType;
}
