package lukaur.grant_management_system.app.web.projects.repositories;

import lukaur.grant_management_system.app.web.model.project.applicant.LegalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegalEntityRepository extends JpaRepository<LegalEntity, Long> {

    @Query("select e from LegalEntity e, Applicant a join a.partners p on p.id= e.id where a.id = :aplicantId")
    List<LegalEntity> findAllPartnersByApplicantId(@Param("aplicantId") Long Id);


}

