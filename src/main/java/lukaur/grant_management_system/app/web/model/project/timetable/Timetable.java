package lukaur.grant_management_system.app.web.model.project.timetable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    List<Task> tasks = new ArrayList<>();

    private LocalDate projectStart;

    private LocalDate projectEnd;

}
