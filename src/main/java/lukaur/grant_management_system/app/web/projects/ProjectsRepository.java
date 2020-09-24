package lukaur.grant_management_system.app.web.projects;

import lukaur.grant_management_system.app.web.model.User;
import lukaur.grant_management_system.app.web.model.project.Project;
import lukaur.grant_management_system.app.web.model.project.timetable.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByUser(User user);

    List<Task> findAllTasksById(Long id);

    List<Project> findAllByCallForProjectsId(Long id);
}
