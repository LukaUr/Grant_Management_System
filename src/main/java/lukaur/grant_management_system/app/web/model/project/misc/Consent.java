package lukaur.grant_management_system.app.web.model.project.misc;

import lukaur.grant_management_system.app.web.model.dictionaries.ConsentText;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(of = "consentText")
@EqualsAndHashCode(of = "consentText")
public class Consent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ConsentText consentText;

    private ConsentOption consentOption;
}
