package lukaur.grant_management_system.app.web.model.project.timetable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@EqualsAndHashCode
@DateComparator
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Date taskStart;

    private Date taskEnd;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BudgetEntry> budgetEntryList = new ArrayList<>();
}
