package lukaur.grant_management_system.app.web.model.project.timetable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateCompareValidator implements ConstraintValidator<DateComparator, Task> {

    @Override
    public void initialize(DateComparator constraint) {
    }

    @Override
    public boolean isValid(Task task, ConstraintValidatorContext context) {
        if (task.equals(new Task())) {

            return true;
        }
        try {
            return !task.getTaskStart().after(task.getTaskEnd());
        } catch (Exception ignore) {

        }
        return true;
        }
}
