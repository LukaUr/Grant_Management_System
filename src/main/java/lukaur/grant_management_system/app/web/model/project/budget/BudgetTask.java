package lukaur.grant_management_system.app.web.model.project.budget;

import lukaur.grant_management_system.app.web.model.project.timetable.Task;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class BudgetTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Task task;

    @OneToMany
    private List<BudgetEntry> budgetEntryList = new ArrayList<>();

}
