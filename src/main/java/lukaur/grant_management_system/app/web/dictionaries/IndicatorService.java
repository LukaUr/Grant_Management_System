package lukaur.grant_management_system.app.web.dictionaries;

import lombok.RequiredArgsConstructor;
import lukaur.grant_management_system.app.web.model.dictionaries.Indicator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndicatorService {
    private final IndicatorRepository indicatorRepository;

    public List<Indicator> findAll() {
        return indicatorRepository.findAll();
    }

    protected void add(Indicator indicator) {
        indicatorRepository.save(indicator);
    }

    protected Indicator find(Long id){return indicatorRepository.getOne(id);}

    protected boolean delete(Indicator indicator) {
        try {
            indicatorRepository.delete(indicator);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    protected boolean update(Indicator indicator) {
        Indicator toBeUpdated = indicatorRepository.getOne(indicator.getId());
        if(toBeUpdated.getId() != null) {
            toBeUpdated.setName(indicator.getName());
            toBeUpdated.setDescription(indicator.getDescription());
            toBeUpdated.setIndicatorType(indicator.getIndicatorType());
            indicatorRepository.save(toBeUpdated);
            return true;
        }
        return false;
    }
}
