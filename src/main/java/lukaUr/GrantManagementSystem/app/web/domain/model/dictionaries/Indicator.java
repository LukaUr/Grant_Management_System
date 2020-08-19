package lukaUr.GrantManagementSystem.app.web.domain.model.dictionaries;

import lukaUr.GrantManagementSystem.app.web.domain.model.dictionaries.IndicatorType;

import java.time.Year;

public class Indicator {

    private Long id;

    private IndicatorType indicatorType;

    private String name;

    private String description;

    private String value;

    private Year plannedchievement;

}
