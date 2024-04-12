package senla.dto.user;
import lombok.*;
import senla.dto.role.RoleDTO;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserShortInfoDTO {
    private String firstName;
    private String secondName;
    private String email;

    private RoleDTO role;
}
