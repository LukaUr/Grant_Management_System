package lukaUr.grantManagementSystem.app.web.dictionaries;

import lombok.RequiredArgsConstructor;
import lukaUr.grantManagementSystem.app.web.model.dictionaries.ConsentText;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsentTextService {

    private final ConsentTextRepository consentTextRepository;

    protected List<ConsentText> findAll() {
        return consentTextRepository.findAll();
    }

    protected void add(ConsentText consentText) {
        consentTextRepository.save(consentText);
    }

}
