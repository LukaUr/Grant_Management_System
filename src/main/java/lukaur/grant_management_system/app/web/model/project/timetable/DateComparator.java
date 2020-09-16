package lukaur.grant_management_system.app.web.model.project.timetable;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateCompareValidator.class)
public @interface DateComparator {
    String message() default "Start date must be before the end date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
