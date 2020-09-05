package lukaUr.grantManagementSystem.app.web.projects;

import lukaUr.grantManagementSystem.app.web.model.User;
import lukaUr.grantManagementSystem.app.web.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByUser(User user);
}
