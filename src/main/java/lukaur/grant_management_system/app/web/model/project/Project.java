package lukaur.grant_management_system.app.web.model.project;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lukaur.grant_management_system.app.web.model.User;
import lukaur.grant_management_system.app.web.model.project.budget.Budget;
import lukaur.grant_management_system.app.web.model.project.applicant.Applicant;
import lukaur.grant_management_system.app.web.model.CallForProjects;
import lukaur.grant_management_system.app.web.model.project.misc.Consent;
import lukaur.grant_management_system.app.web.model.project.misc.ProjectDetails;
import lukaur.grant_management_system.app.web.model.project.timetable.Timetable;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(nullable = false)
    @Length(min = 5, max = 255)
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    private CallForProjects callForProjects;

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