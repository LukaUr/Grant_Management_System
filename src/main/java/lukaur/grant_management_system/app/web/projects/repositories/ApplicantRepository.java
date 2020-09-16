package lukaur.grant_management_system.app.web.projects.repositories;

import lukaur.grant_management_system.app.web.model.project.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
