package lukaUr.GrantManagementSystem.app.web.domain.model.project;


import lukaUr.GrantManagementSystem.app.web.domain.model.project.budget.Budget;
import lukaUr.GrantManagementSystem.app.web.domain.model.project.capacity.OperationalCapacity;
import lukaUr.GrantManagementSystem.app.web.domain.model.project.applicant.Applicant;
import lukaUr.GrantManagementSystem.app.web.domain.model.project.misc.CallForPRojects;
import lukaUr.GrantManagementSystem.app.web.domain.model.project.misc.Consents;
import lukaUr.GrantManagementSystem.app.web.domain.model.project.misc.ProjectDetails;
import lukaUr.GrantManagementSystem.app.web.domain.model.project.timetable.Timetable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime created;

    private LocalDateTime updated;

    private String name;

    private CallForPRojects callForPRojects;

    private Applicant applicant;

    private OperationalCapacity operationalCapacity;

    private ProjectDetails projectDetails;

    private Timetable timetable;

    private Budget budget;

    private Consents consents;












}
