package lukaUr.grantManagementSystem.app.web.dictionaries;

import lukaUr.grantManagementSystem.app.web.model.dictionaries.ConsentText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentTextRepository extends JpaRepository<ConsentText, Long> {

}
