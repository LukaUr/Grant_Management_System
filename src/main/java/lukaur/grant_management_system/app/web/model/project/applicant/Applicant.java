package lukaur.grant_management_system.app.web.model.project.applicant;

import java.util.ArrayList;
import java.util.List;


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

    @OneToOne(cascade = CascadeType.ALL)
    private LegalEntity identity;

    @OneToOne(cascade = CascadeType.ALL)
    private Person contactPerson;

    @OneToOne(cascade = CascadeType.ALL)
    private Person legalRepresentative;

    @OneToMany
    private List<LegalEntity> partners = new ArrayList<>();

}
