package lukaUr.grantManagementSystem.app.web.model.project.budget;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fundingSource;

    private Double fundingShare;

    private BigDecimal fundingAmount;
}
