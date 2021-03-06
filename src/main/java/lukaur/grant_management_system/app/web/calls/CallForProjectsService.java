package lukaur.grant_management_system.app.web.calls;

import lombok.RequiredArgsConstructor;
import lukaur.grant_management_system.app.web.model.CallForProjects;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CallForProjectsService {

    private final CallForProjectsRepository callRepository;

    public List<CallForProjects> findAll() {
        return callRepository.findAll();
    }

    protected void add(CallForProjects callForProjects) {
        callRepository.save(callForProjects);
    }

    public CallForProjects find(Long id) {
        return callRepository.getOne(id);
    }

    protected boolean update(CallForProjects call) {
        CallForProjects toBeUpdated = callRepository.getOne(call.getId());
        toBeUpdated.setName(call.getName());
        toBeUpdated.setDescription(call.getDescription());
        toBeUpdated.setFunding(call.getFunding());
        toBeUpdated.setConsentSet(call.getConsentSet());
        try {
            callRepository.save(toBeUpdated);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean delete(Long id) {
        try {
            callRepository.delete(callRepository.getOne(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
