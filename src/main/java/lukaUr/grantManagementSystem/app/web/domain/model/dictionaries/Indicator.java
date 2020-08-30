package lukaUr.grantManagementSystem.app.web.domain.model.dictionaries;

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
@ToString(of = "name")
@EqualsAndHashCode(of = "id")
public class Indicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private IndicatorType indicatorType;

    private String name;

    private String description;

    private String value;

    private Year plannedchievement;

}
