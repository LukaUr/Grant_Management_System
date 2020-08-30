package lukaUr.grantManagementSystem.app.web.domain.model.project.applicant;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@ToString(of = "name")
@EqualsAndHashCode(of = "id")
public class LegalEntity {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String legalForm;

    private String registationNumber;

    private String VATNumber;

    private String bankAccountNumber;

    private String email;

    private String phone;

    private String country;

    private String city;

    private String postCode;

    private String addressLine1;

    private String addressLine2;

}
