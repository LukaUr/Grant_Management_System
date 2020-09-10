package lukaur.grant_management_system.app.web.projects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lukaur.grant_management_system.app.web.dictionaries.IndicatorService;
import lukaur.grant_management_system.app.web.model.dictionaries.Indicator;
import lukaur.grant_management_system.app.web.model.project.Project;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@Slf4j
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectsService projectsService;
    private final IndicatorService indicatorService;

    @ModelAttribute("indicators")
    private List<Indicator> indicators() {
        return indicatorService.findAll();
    }


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

    @PostMapping("/save")
    public String processProjectSave(@Valid Project project,
                                     BindingResult result) {
        if (result.hasErrors()) {
            log.error("Cant save a project");
            return "project?projet";
        }
        log.info("Attempting to save project");
        projectsService.save(project);
        log.info("Project saved");
        return "redirect:/menu";
    }
}

