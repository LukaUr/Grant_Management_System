package lukaur.grant_management_system.app.web.projects;

import lombok.RequiredArgsConstructor;
import lukaur.grant_management_system.app.web.model.User;
import lukaur.grant_management_system.app.web.model.project.Project;
import lukaur.grant_management_system.app.web.model.project.applicant.LegalEntity;
import lukaur.grant_management_system.app.web.projects.parts.ApplicantRepository;
import lukaur.grant_management_system.app.web.projects.parts.LegalEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class ProjectsService {

    private final ProjectsRepository projectsRepository;
    private final LegalEntityRepository legalEntityRepository;
    private final ApplicantRepository applicantRepository;

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
        List<LegalEntity> partnersFromBase = getPartners(project.getApplicant().getId());
        List<LegalEntity> partnersFromForm = project.getApplicant().getPartners();
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
        projectsRepository.save(project);

    }

    public List<LegalEntity> getPartners(Long id) {
        return legalEntityRepository.findAllPartnersByApplicantId(id);
    }
}