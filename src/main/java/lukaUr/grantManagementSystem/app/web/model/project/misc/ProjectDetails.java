package lukaUr.grantManagementSystem.app.web.model.project.misc;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lukaUr.grantManagementSystem.app.web.model.dictionaries.Indicator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class ProjectDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mainObjective;

    private String projectSubstantation;

    private String actionDescription;

    private String expectedResults;

    private String sustainability;

    private String financialCapacity;

    private String oparationalCapacity;

    @OneToMany
    private Set<Indicator> indicators;




}
