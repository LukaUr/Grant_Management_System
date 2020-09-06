package lukaur.grant_management_system.app.web.model.dictionaries;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Data
@ToString(of = "declarationText")
@EqualsAndHashCode(of = "id")
public class ConsentText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    @Length(min = 10, max = 1000)
    private String declarationText;

}
