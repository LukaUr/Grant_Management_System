package lukaur.grant_management_system.app.web.projects.repositories;

import lukaur.grant_management_system.app.web.model.project.timetable.Task;
import lukaur.grant_management_system.app.web.model.project.timetable.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    @Query("select t from Task t, Timetable timT join timT.tasks tasks on tasks.id= t.id where timT.id = :timetableId")
    List<Task> findAllTasksByTimetableId(@Param("timetableId") Long Id);

}
