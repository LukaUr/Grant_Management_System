package lukaur.grant_management_system.app.web.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lukaur.grant_management_system.app.web.model.dictionaries.ConsentText;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Column(nullable = false, length = 200)
    @Size(min = 5, max = 200)
    private String name;

    @Column(nullable = false, length = 1000)
    @Size(min = 5, max = 1000)
    private String description;

    @Column(nullable = false)
    @DecimalMin("0")
    @NotNull
    private BigDecimal funding;

    @ManyToMany
    Set<ConsentText> consentSet = new HashSet<>();

}
