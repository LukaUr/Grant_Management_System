package lukaUr.GrantManagementSystem.app.web.domain.model.project.budget;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalValue;

    private  BigDecimal totalFunding;

    @OneToOne
    private FinancialEngineering financialEngineering;

    @OneToOne
    private FinancialPlan financialPlan;

}
