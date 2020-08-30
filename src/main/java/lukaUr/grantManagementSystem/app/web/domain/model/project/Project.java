package lukaUr.grantManagementSystem.app.web.domain.model.project;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lukaUr.grantManagementSystem.app.web.domain.model.project.budget.Budget;
import lukaUr.grantManagementSystem.app.web.domain.model.project.applicant.Applicant;
import lukaUr.grantManagementSystem.app.web.domain.model.CallForProjects;
import lukaUr.grantManagementSystem.app.web.domain.model.project.misc.Consent;
import lukaUr.grantManagementSystem.app.web.domain.model.project.misc.ProjectDetails;
import lukaUr.grantManagementSystem.app.web.domain.model.project.timetable.Timetable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString(of = "name")
@EqualsAndHashCode(of = "id")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime created;

    private LocalDateTime updated;

    private String name;

    @OneToOne
    private CallForProjects callForPRojects;

    @OneToOne
    private Applicant applicant;

    @OneToOne
    private ProjectDetails projectDetails;

    @OneToOne
    private Timetable timetable;

    @OneToOne
    private Budget budget;

    @OneToMany
    private Set<Consent> consents = new HashSet<>();

    private String controlSum;












}
