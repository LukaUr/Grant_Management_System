package lukaUr.grantManagementSystem.app.web.dictionaries;

import lombok.RequiredArgsConstructor;
import lukaUr.grantManagementSystem.app.web.calls.CallForProjectsRepository;
import lukaUr.grantManagementSystem.app.web.model.CallForProjects;
import lukaUr.grantManagementSystem.app.web.model.dictionaries.ConsentText;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    protected ConsentText find(Long id){return consentTextRepository.getOne(id);}

    protected boolean delete(ConsentText consentText) {
        try {
            consentTextRepository.delete(consentText);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    protected boolean update(ConsentText consentText) {
        ConsentText toBeUpdated = consentTextRepository.getOne(consentText.getId());
        if(toBeUpdated.getId() != null) {
            toBeUpdated.setDeclarationText(consentText.getDeclarationText());
            consentTextRepository.save(toBeUpdated);
            return true;
        }
        return false;
    }

}
