package lukaUr.grantManagementSystem.app.web.menu;

import lombok.RequiredArgsConstructor;
import lukaUr.grantManagementSystem.app.web.calls.CallForProjectsService;
import lukaUr.grantManagementSystem.app.web.model.CallForProjects;
import lukaUr.grantManagementSystem.app.web.model.User;
import lukaUr.grantManagementSystem.app.web.model.project.Project;
import lukaUr.grantManagementSystem.app.web.projects.ProjectsService;
import lukaUr.grantManagementSystem.app.web.users.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class UserMenuController {

    private final CallForProjectsService callService;
    private final ProjectsService projectsService;
    private final UserService userService;

    @ModelAttribute("calls")
    private List<CallForProjects> call() {
        return callService.findAll();
    }

    @ModelAttribute("userName")
    private String userName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    @GetMapping
    public String showMainUserMenu(Model model,
                                   Principal principal) {
        String userName = principal.getName();
        User user = userService.findByName(userName);
        List<Project> projects = projectsService.findAllByUser(user);
        model.addAttribute("projects", projects);
        return "menu/mainUserMenu";
    }

    @GetMapping("/details")
    public String showCallDetails(@RequestParam Long id,
                                  Model model) {
        CallForProjects call = callService.find(id);
        model.addAttribute("call", call);
        return "menu/callDetails";
    }

}
