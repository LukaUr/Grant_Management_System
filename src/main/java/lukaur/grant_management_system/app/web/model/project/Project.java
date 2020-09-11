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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @ManyToOne(cascade = CascadeType.MERGE)
    private CallForProjects callForProjects;

    @OneToOne(cascade = CascadeType.ALL)
    private Applicant applicant;

    @OneToOne(cascade = CascadeType.ALL)
    private ProjectDetails projectDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private Timetable timetable;

    @OneToOne(cascade = CascadeType.ALL)
    private Budget budget;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Consent> consents = new ArrayList<>();

    private String controlSum;

    @PrePersist
    public void prePersist() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated = LocalDateTime.now();
    }
}
