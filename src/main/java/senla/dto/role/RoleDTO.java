package senla.dto.role;
import lombok.*;
import senla.enums.RoleName;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private RoleName roleName;
}
