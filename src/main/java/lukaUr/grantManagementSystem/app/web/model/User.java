package lukaUr.grantManagementSystem.app.web.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lukaUr.grantManagementSystem.app.web.model.project.Project;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString(of = "name")
@EqualsAndHashCode(of = "id")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    @Length(min = 4, max = 20)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String password;

    @OneToMany
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
