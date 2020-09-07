package lukaur.grant_management_system.app.web.projects;

import lombok.RequiredArgsConstructor;
import lukaur.grant_management_system.app.web.model.project.Project;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectsService projectsService;

    @ModelAttribute("userName")
    private String userName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    @GetMapping("/project")
    public String showProject(@RequestParam Long id, Model model) {
        Project project = projectsService.findById(id);
        model.addAttribute("project", project);
        return "project/project";
    }


}

