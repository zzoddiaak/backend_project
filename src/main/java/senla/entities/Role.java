package senla.entities;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Entity{
    private String roleName;
}
