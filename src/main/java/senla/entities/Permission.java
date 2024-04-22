package senla.entities;

import lombok.*;
import senla.enums.PermissionType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "permissions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "permission_name")
    private PermissionType permissionType;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> role;
}
