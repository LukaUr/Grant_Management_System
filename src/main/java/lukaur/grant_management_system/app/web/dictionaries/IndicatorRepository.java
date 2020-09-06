package lukaur.grant_management_system.app.web.dictionaries;

import lukaur.grant_management_system.app.web.model.dictionaries.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
}
