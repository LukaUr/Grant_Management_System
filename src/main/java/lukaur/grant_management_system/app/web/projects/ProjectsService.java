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
import lukaur.grant_management_system.app.web.model.project.timetable.Timetable;
import lukaur.grant_management_system.app.web.projects.repositories.ApplicantRepository;
import lukaur.grant_management_system.app.web.projects.repositories.LegalEntityRepository;
import lukaur.grant_management_system.app.web.projects.repositories.TaskRepository;
import lukaur.grant_management_system.app.web.projects.repositories.TimetableRepository;
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
    private final TimetableRepository timetableRepository;
    private final TaskRepository taskRepository;

    public Project create(Project project) {
        try {
            Project saved = projectsRepository.save(project);
            return saved;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Project> findAllByUser(User user) {
        return projectsRepository.findAllByUser(user);
    }

    protected Project findById(Long projectId) {
        return projectsRepository.getOne(projectId);
    }

    public void save(Project project) {
//        perserve creation date
        Project oldPRoject = projectsRepository.getOne(project.getId());
        project.setCreated(oldPRoject.getCreated());
//        manage adding, deleting and updateing partners
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
//        manage adding, deleting and updating tasks
        List<Task> tasksFromBase = getTasks(project.getApplicant().getId());
        List<Task> newTasks = project
                .getTimetable()
                .getTasks()
                .stream()
                .filter((t) -> !t.equals(new Task()))
                .collect(Collectors.toList());
        project.getTimetable().setTasks(newTasks);
        taskRepository.saveAll(project.getTimetable().getTasks());
        timetableRepository.save(project.getTimetable());
        tasksFromBase
                .stream()
                .filter(t -> !newTasks.contains(t))
                .forEach(taskRepository::delete);
//        final save
        projectsRepository.save(project);

    }

    public List<LegalEntity> getPartners(Long id) {
        return legalEntityRepository.findAllPartnersByApplicantId(id);
    }

    public List<Task> getTasks(Long id) {
        return timetableRepository.findAllTasksByTimetableId(id);
    }

    public Project initialize(Project project,
                           Principal principal) {
//        set user
        String userName = principal.getName();
        User user = userRepository.getOneByName(userName);
        project.setUser(user);
//        set consents
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
//        set indicators
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
//        save project
        Project saved = this.create(project);
        return saved;
    }
}