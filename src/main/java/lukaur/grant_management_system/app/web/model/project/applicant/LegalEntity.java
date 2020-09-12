package lukaur.grant_management_system.app.web.model.project.applicant;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
@Data
@ToString(of = "name")
@EqualsAndHashCode
public class LegalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String legalForm;

    private String registrationNumber;

    private String vatNumber;

    private String bankAccountNumber;

    private String email;

    private String phone;

    private String country;

    private String city;

    private String postCode;

    private String addressLine1;

    private String addressLine2;

}
