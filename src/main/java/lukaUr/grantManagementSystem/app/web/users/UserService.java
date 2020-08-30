package lukaUr.grantManagementSystem.app.web.users;

import lombok.RequiredArgsConstructor;
import lukaUr.grantManagementSystem.app.web.model.Role;
import lukaUr.grantManagementSystem.app.web.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    protected void registerUser(User user) {
        Role role = roleRepository.getOneByName("ROLE_USER");
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    protected List<User> findAllUsers() {
        return userRepository.findAll();
    }

    protected void deleteUser(Long userId) {
        User user = userRepository.getOne(userId);
        userRepository.delete(user);
    }

    protected User findById(Long id) {
        return userRepository.getOne(id);
    }

    protected void update(User user) {
        User updatedUser = userRepository.getOne(user.getId());
        updatedUser.setRoles(user.getRoles());
        userRepository.save(updatedUser);
    }

}
