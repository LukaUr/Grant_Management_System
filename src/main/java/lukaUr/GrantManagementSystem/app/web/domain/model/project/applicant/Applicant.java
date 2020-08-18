package lukaUr.GrantManagementSystem.app.web.domain.model.project.applicant;

import java.util.HashSet;
import java.util.Set;

public class Applicant {

    private LegalEntity identity;

    private Person contactPerson;

    private Person legalRepresentative;

    private Set<LegalEntity> partners = new HashSet<>();

}
