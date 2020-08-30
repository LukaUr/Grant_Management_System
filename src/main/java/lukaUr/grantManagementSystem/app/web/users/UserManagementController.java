package lukaUr.grantManagementSystem.app.web.users;

import lombok.RequiredArgsConstructor;
import lukaUr.grantManagementSystem.app.web.model.Role;
import lukaUr.grantManagementSystem.app.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserManagementController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping("/show")
    public String showAllUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        Role user = roleRepository.getOneByName("ROLE_USER");
        model.addAttribute("role_user", user);
        Role admin = roleRepository.getOneByName("ROLE_ADMIN");
        model.addAttribute("role_admin", admin);
        return "user/allUsers";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/user/show";
    }

    @GetMapping("/edit")
    public String showEditUserForm(@RequestParam Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        Role roleUser = roleRepository.getOneByName("ROLE_USER");
        model.addAttribute("role_user", roleUser);
        Role admin = roleRepository.getOneByName("ROLE_ADMIN");
        model.addAttribute("role_admin", admin);
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "user/editUser";
    }

    @PostMapping("/edit")
    public String processEditUserForm(@Valid User user,
                                      @RequestParam(required = false) boolean isUser,
                                      @RequestParam(required = false) boolean isAdmin) {
        if(isUser){
            user.getRoles().add(roleRepository.getOneByName("ROLE_USER"));
        }
        if(isAdmin){
            user.getRoles().add(roleRepository.getOneByName("ROLE_ADMIN"));
        }
        userService.update(user);
        return "redirect:/user/show";
    }
}
