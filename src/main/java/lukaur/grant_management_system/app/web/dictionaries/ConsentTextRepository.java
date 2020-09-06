package lukaur.grant_management_system.app.web.dictionaries;

import lukaur.grant_management_system.app.web.model.dictionaries.ConsentText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentTextRepository extends JpaRepository<ConsentText, Long> {

}
