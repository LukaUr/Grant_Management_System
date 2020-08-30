package lukaUr.grantManagementSystem.app.web.model;

import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString(of = "name")
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
