package lukaUr.GrantManagementSystem.app.web.domain.model.project.timetable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Timetable {

    private Long id;

    List<Task> tasks = new ArrayList<>();

    private LocalDate projectStart;

    private LocalDate projectEnd;

}
