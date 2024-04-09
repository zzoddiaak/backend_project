package senla.entities;

import lombok.*;
import senla.enums.RoleName;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Entity {
    private RoleName roleName;
}

