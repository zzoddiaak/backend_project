package senla.dto.user;

import lombok.*;
import senla.dto.role.RoleDTO;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFullInfoDTO {
    private long uuid;
    private String firstName;
    private String secondName;
    private String user_password;
    private String email;
    private RoleDTO role;
}
