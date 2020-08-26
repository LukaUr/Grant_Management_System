package lukaUr.GrantManagementSystem.app.web.domain.model.project.misc;

import lukaUr.GrantManagementSystem.app.web.domain.model.dictionaries.ConsentText;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(of = "consentText")
@EqualsAndHashCode(of = "id")
public class Consent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ConsentText consentText;

    private ConsentOption consentOption;
}
