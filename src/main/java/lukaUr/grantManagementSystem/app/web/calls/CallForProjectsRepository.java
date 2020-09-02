package lukaUr.grantManagementSystem.app.web.calls;

import lukaUr.grantManagementSystem.app.web.model.CallForProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallForProjectsRepository extends JpaRepository<CallForProjects, Long> {
}
