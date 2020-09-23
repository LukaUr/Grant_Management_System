package lukaur.grant_management_system.app.web.model.project;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lukaur.grant_management_system.app.web.model.User;
import lukaur.grant_management_system.app.web.model.project.applicant.Applicant;
import lukaur.grant_management_system.app.web.model.CallForProjects;
import lukaur.grant_management_system.app.web.model.project.misc.Consent;
import lukaur.grant_management_system.app.web.model.project.misc.ProjectDetails;
import lukaur.grant_management_system.app.web.model.project.misc.ProjectIndicator;
import lukaur.grant_management_system.app.web.model.project.timetable.Timetable;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Length(min = 5, max = 255, message = "Project name: length must be between 5 and 255")
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    private CallForProjects callForProjects;

    @OneToOne
    private Applicant applicant;

    @OneToOne(cascade = CascadeType.ALL)
    private ProjectDetails projectDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private Timetable timetable;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Consent> consents = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProjectIndicator> indicators;

    private BigDecimal totalValue;

    private BigDecimal grantValue;

    @PrePersist
    public void prePersist() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated = LocalDateTime.now();
    }
}
