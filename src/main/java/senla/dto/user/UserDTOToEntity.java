package senla.dto.user;

import lombok.*;
import senla.dto.role.RoleDTO;
import senla.dto.role.RoleDTOToEntity;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOToEntity {
    private String firstName;
    private String secondName;
    private String email;
    private RoleDTOToEntity role;

}
