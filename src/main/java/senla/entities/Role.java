package senla.entities;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Entity {
    private RoleName roleName;

    public enum RoleName {
        ADMIN,
        PSYCHOLOGIST,
        CLIENT
    }
}
