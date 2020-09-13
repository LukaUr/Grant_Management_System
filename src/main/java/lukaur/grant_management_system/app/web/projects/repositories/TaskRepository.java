package lukaur.grant_management_system.app.web.projects.repositories;

import lukaur.grant_management_system.app.web.model.project.timetable.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
