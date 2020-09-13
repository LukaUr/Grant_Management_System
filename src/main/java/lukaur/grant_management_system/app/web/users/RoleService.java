package lukaur.grant_management_system.app.web.users;

import lombok.RequiredArgsConstructor;
import lukaur.grant_management_system.app.web.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.getOneByName(name);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
