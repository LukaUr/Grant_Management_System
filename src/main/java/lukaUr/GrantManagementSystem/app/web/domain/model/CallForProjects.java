package lukaUr.GrantManagementSystem.app.web.domain.model;

import lukaUr.GrantManagementSystem.app.web.domain.model.dictionaries.ConsentText;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CallForProjects {

    private Long id;

    private String name;

    private String description;

    private BigDecimal funding;

    Set<ConsentText> consentSet = new HashSet<>();

}
