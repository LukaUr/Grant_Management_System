package lukaur.grant_management_system.app.web.model.project.misc;

import lombok.Data;
import lukaur.grant_management_system.app.web.model.dictionaries.Indicator;

import javax.persistence.*;

@Entity
@Data
public class ProjectIndicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Indicator indicator;

    private Double value;

    private Integer plannedAchievement;
}
