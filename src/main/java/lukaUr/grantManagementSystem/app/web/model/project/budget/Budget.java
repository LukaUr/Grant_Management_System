package lukaUr.grantManagementSystem.app.web.model.project.budget;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @OneToMany
    private Set<Funding> fundingSet;

    @OneToMany
    private List<BudgetTask> budgetTasks = new ArrayList<>();

}
