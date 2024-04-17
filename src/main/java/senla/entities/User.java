package senla.entities;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Entity{
    private String firstname;
    private String secondname;
    private String userPassword;
    private String email;
    private Role role;

}
