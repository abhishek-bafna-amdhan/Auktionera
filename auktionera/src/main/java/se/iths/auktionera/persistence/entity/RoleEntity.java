package se.iths.auktionera.persistence.entity;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<AccountEntity> accountEntities = new HashSet<>();
}
