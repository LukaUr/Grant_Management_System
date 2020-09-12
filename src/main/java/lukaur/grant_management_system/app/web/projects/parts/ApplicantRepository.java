package lukaur.grant_management_system.app.web.projects.parts;

import lukaur.grant_management_system.app.web.model.project.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
