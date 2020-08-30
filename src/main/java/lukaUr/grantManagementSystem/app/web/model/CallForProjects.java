package lukaUr.grantManagementSystem.app.web.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lukaUr.grantManagementSystem.app.web.model.dictionaries.ConsentText;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString(of = "name")
@EqualsAndHashCode(of = "id")
public class CallForProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal funding;

    @ManyToMany
    Set<ConsentText> consentSet = new HashSet<>();

}
