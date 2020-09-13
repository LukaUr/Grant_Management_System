package lukaur.grant_management_system.app.web.projects.repositories;

import lukaur.grant_management_system.app.web.model.project.misc.ProjectIndicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectIndicatorRepository extends JpaRepository<ProjectIndicator, Long> {
}
