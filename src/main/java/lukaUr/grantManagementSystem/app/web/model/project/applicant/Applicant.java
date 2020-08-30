package lukaUr.grantManagementSystem.app.web.model.project.applicant;

import java.util.HashSet;
import java.util.Set;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(of = "identity.name")
@EqualsAndHashCode(of = "id")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private LegalEntity identity;

    @OneToOne
    private Person contactPerson;

    @OneToOne
    private Person legalRepresentative;

    @OneToMany
    private Set<LegalEntity> partners = new HashSet<>();

}
