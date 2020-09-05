package lukaUr.grantManagementSystem.app.web.projects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lukaUr.grantManagementSystem.app.web.calls.CallForProjectsService;
import lukaUr.grantManagementSystem.app.web.model.CallForProjects;
import lukaUr.grantManagementSystem.app.web.model.User;
import lukaUr.grantManagementSystem.app.web.model.project.Project;
import lukaUr.grantManagementSystem.app.web.users.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class InitialProjectController {

    private final CallForProjectsService callForProjectsService;
    private final ProjectsService projectsService;
    private final UserService userService;

    @ModelAttribute("userName")
    private String userName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    @GetMapping("/apply")
    public String showIniaitalProjectForm(@RequestParam Long id, Model model) {
        CallForProjects call = callForProjectsService.find(id);
        model.addAttribute("call", call);
        model.addAttribute("project", new Project());
        return "project/initialProject";
    }

    @PostMapping("/apply")
    public String processInitialProjectForm(@Valid Project project,
                                            BindingResult result,
                                            Model model,
                                            Principal principal) {
        if (result.hasErrors()) {
            Long id = project.getCallForProjects().getId();
            CallForProjects call = callForProjectsService.find(id);
            model.addAttribute("call", call);
            return "project/initialProject";
        }
        String userName = principal.getName();
        User user = userService.findByName(userName);
        project.setUser(user);
        boolean success = projectsService.create(project);
        log.info("project saved: " + success);
        if (success) {
            return "redirect:/project/project";
        }
        log.error("Can't save");
        model.addAttribute("message", "Can not create new project");
        return "project/initialProject";
    }
}
