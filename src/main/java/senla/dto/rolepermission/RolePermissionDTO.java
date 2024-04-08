package senla.dto.rolepermission;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionDTO {
    private long roleId;
    private long permissionId;
}
