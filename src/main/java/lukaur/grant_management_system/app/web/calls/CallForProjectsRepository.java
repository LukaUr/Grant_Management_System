package lukaur.grant_management_system.app.web.calls;

import lukaur.grant_management_system.app.web.model.CallForProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallForProjectsRepository extends JpaRepository<CallForProjects, Long> {
}
