package lukaUr.GrantManagementSystem.app.web.domain.model.project.budget;

import java.math.BigDecimal;
import java.time.Year;

public class BudgetEntry {

    private Long id;

    private Year year;

    private Integer quarter;

    private BigDecimal totalAmount;

    private BigDecimal totalFunding;

    private BigDecimal selfFunding;

}
