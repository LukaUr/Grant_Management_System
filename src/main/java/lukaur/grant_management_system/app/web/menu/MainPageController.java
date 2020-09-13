package lukaur.grant_management_system.app.web.menu;

import lombok.RequiredArgsConstructor;
import lukaur.grant_management_system.app.web.model.Role;
import lukaur.grant_management_system.app.web.model.User;
import lukaur.grant_management_system.app.web.users.RoleService;
import lukaur.grant_management_system.app.web.users.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final UserService userService;
    private final RoleService roleService;


    @ModelAttribute("userName")
    private String userName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    @GetMapping
    public String showMainPage(Principal principal) {
        Set<Role> userRoles = getLoggedUserRole(principal);
        if(userRoles.size() > 0 && userRoles.contains(roleService.findByName("ROLE_ADMIN"))){
            return "index";
        }
        return "redirect:/menu";
    }

    private Set<Role> getLoggedUserRole(Principal principal) {
        User user = userService.findByName(principal.getName());
        return user.getRoles();

    }
}
