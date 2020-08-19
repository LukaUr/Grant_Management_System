package lukaUr.GrantManagementSystem.app.web.domain.model.project;


import lukaUr.GrantManagementSystem.app.web.domain.model.project.budget.Budget;
import lukaUr.GrantManagementSystem.app.web.domain.model.project.applicant.Applicant;
import lukaUr.GrantManagementSystem.app.web.domain.model.CallForProjects;
import lukaUr.GrantManagementSystem.app.web.domain.model.project.misc.Consent;
import lukaUr.GrantManagementSystem.app.web.domain.model.project.misc.ProjectDetails;
import lukaUr.GrantManagementSystem.app.web.domain.model.project.timetable.Timetable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime created;

    private LocalDateTime updated;

    private String name;

    private CallForProjects callForPRojects;

    private Applicant applicant;

    private ProjectDetails projectDetails;

    private Timetable timetable;

    private Budget budget;

    private Set<Consent> consents = new HashSet<>();

    private String controlSum;












}
