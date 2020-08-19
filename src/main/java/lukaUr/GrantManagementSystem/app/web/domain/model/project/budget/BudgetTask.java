package lukaUr.GrantManagementSystem.app.web.domain.model.project.budget;

import lukaUr.GrantManagementSystem.app.web.domain.model.project.timetable.Task;

import java.util.ArrayList;
import java.util.List;

public class BudgetTask {

    private Long id;

    private Task task;

    private List<BudgetEntry> budgetEntryList = new ArrayList<>();

}
