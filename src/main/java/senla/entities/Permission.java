package senla.entities;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends Entity{

    private String permissionName;
}
