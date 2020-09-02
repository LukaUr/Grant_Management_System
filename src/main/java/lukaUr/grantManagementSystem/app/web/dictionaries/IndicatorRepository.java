package lukaUr.grantManagementSystem.app.web.dictionaries;

import lukaUr.grantManagementSystem.app.web.model.dictionaries.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
}
