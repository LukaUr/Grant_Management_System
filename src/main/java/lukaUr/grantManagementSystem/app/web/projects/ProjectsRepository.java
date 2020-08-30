package lukaUr.grantManagementSystem.app.web.projects;

import lukaUr.grantManagementSystem.app.web.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {
}
