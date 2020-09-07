package lukaur.grant_management_system.app.web.projects;

import lombok.RequiredArgsConstructor;
import lukaur.grant_management_system.app.web.model.User;
import lukaur.grant_management_system.app.web.model.project.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectsService {

    private final ProjectsRepository projectsRepository;

    protected Project create(Project project) {
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
}
