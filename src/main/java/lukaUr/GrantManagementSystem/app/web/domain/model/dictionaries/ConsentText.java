package lukaUr.GrantManagementSystem.app.web.domain.model.dictionaries;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@ToString(of = "declarationText")
@EqualsAndHashCode(of = "id")
public class ConsentText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String declarationText;
}
