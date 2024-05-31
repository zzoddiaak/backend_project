package senla.entities;

import lombok.*;
import senla.enums.RoleName;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "roles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    private List<Permission> permission;
}

