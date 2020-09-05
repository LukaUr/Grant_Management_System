package lukaUr.grantManagementSystem.app.web.projects;

import lombok.RequiredArgsConstructor;
import lukaUr.grantManagementSystem.app.web.model.User;
import lukaUr.grantManagementSystem.app.web.model.project.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectsService {

    private final ProjectsRepository projectsRepository;

    protected boolean create(Project project) {
        try {
            projectsRepository.save(project);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Project> findAllByUser(User user) {
        return projectsRepository.findAllByUser(user);
    }
}
