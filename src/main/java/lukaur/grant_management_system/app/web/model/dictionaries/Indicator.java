package lukaur.grant_management_system.app.web.model.dictionaries;

import java.time.Year;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@ToString(of = "name")
@EqualsAndHashCode(of = "id")
public class Indicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private IndicatorType indicatorType;

    @Size(min = 5, max = 255)
    private String name;

    @Column(length = 1000)
    @Size(min = 5, max = 1000)
    private String description;

}
