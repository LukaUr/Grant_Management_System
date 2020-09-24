package lukaur.grant_management_system.app.web.projects;

import lombok.RequiredArgsConstructor;
import lukaur.grant_management_system.app.web.calls.CallForProjectsRepository;
import lukaur.grant_management_system.app.web.dictionaries.IndicatorRepository;
import lukaur.grant_management_system.app.web.model.User;
import lukaur.grant_management_system.app.web.model.project.Project;
import lukaur.grant_management_system.app.web.model.project.applicant.LegalEntity;
import lukaur.grant_management_system.app.web.model.project.misc.Consent;
import lukaur.grant_management_system.app.web.model.project.misc.ProjectIndicator;
import lukaur.grant_management_system.app.web.model.project.timetable.Task;
import lukaur.grant_management_system.app.web.projects.repositories.ApplicantRepository;
import lukaur.grant_management_system.app.web.projects.repositories.LegalEntityRepository;
import lukaur.grant_management_system.app.web.projects.repositories.TaskRepository;
import lukaur.grant_management_system.app.web.users.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class ProjectsService {

    private final ProjectsRepository projectsRepository;
    private final LegalEntityRepository legalEntityRepository;
    private final ApplicantRepository applicantRepository;
    private final UserRepository userRepository;
    private final CallForProjectsRepository callRepository;
    private final IndicatorRepository indicatorRepository;
    private final TaskRepository taskRepository;

    public Project create(Project project) {
        try {
            return projectsRepository.save(project);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Project> findAllByUser(User user) {
        return projectsRepository.findAllByUser(user);
    }

    public Project findById(Long projectId) {
        return projectsRepository.getOne(projectId);
    }

    public void save(Project project) {
        perserveCreationDate(project);
        managePartners(project);
        manageTimetable(project);
        projectsRepository.save(project);
    }

    public List<LegalEntity> getPartners(Long id) {
        return legalEntityRepository.findAllPartnersByApplicantId(id);
    }

    public List<Task> getTasks(Long id) {
        return projectsRepository.findAllTasksById(id);
    }

    public Project initialize(Project project,
                           Principal principal) {
        setUser(project, principal);
        setConsents(project);
        setIndicators(project);
        return this.create(project);
    }

    private void setIndicators(Project project) {
        List<ProjectIndicator> indicators = indicatorRepository
                .findAll()
                .stream()
                .map(i -> {
                    ProjectIndicator indicator = new ProjectIndicator();
                    indicator.setIndicator(i);
                    return indicator;
                })
                .collect(Collectors.toList());
        project.setIndicators(indicators);
    }

    private void setConsents(Project project) {
        List<Consent> consents = callRepository
                .getOne(project.getCallForProjects().getId()).getConsentSet()
                .stream()
                .map(c -> {
                    Consent consent = new Consent();
                    consent.setConsentText(c);
                    return consent;
                })
                .collect(Collectors.toList());
        project.setConsents(consents);
    }

    private void setUser(Project project, Principal principal) {
        String userName = principal.getName();
        User user = userRepository.getOneByName(userName);
        project.setUser(user);
    }

    private void perserveCreationDate(Project project) {
        Project oldPRoject = projectsRepository.getOne(project.getId());
        project.setCreated(oldPRoject.getCreated());
    }

    private void managePartners(Project project) {
        List<LegalEntity> partnersFromBase = getPartners(project.getApplicant().getId());
        List<LegalEntity> newPartners = project
                .getApplicant()
                .getPartners()
                .stream()
                .filter((p) -> !p.equals(new LegalEntity()))
                .collect(Collectors.toList());
        project.getApplicant().setPartners(newPartners);
        legalEntityRepository.save(project.getApplicant().getIdentity());
        legalEntityRepository.saveAll(project.getApplicant().getPartners());
        applicantRepository.save(project.getApplicant());
        partnersFromBase
                .stream()
                .filter(p -> !newPartners.contains(p))
                .forEach(legalEntityRepository::delete);
    }

    private void manageTimetable(Project project) {
        List<Task> tasksFromBase = getTasks(project.getApplicant().getId());
        List<Task> newTasks = project
                .getTasks()
                .stream()
                .filter((t) -> !t.equals(new Task()))
                .collect(Collectors.toList());
        project.setTasks(newTasks);
        taskRepository.saveAll(project.getTasks());
        tasksFromBase
                .stream()
                .filter(t -> !newTasks.contains(t))
                .forEach(taskRepository::delete);
    }

    protected void delete(Project project) {
        projectsRepository.delete(project);
    }

    public List<Project> findAllByCallId(Long id) {
        return projectsRepository.findAllByCallForProjectsId(id);
    }
}