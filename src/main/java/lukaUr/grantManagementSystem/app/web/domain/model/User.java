package lukaUr.grantManagementSystem.app.web.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lukaUr.grantManagementSystem.app.web.domain.model.project.Project;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString(of = "name")
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    @OneToMany
    private Set<Project> projects = new HashSet<>();

}
