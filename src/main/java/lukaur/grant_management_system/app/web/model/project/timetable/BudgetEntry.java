package lukaur.grant_management_system.app.web.model.project.timetable;

import java.math.BigDecimal;
import java.time.Year;

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
public class BudgetEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Year year;

    private BigDecimal totalAmount;

    private BigDecimal totalFunding;

    private BigDecimal selfFunding;

}
