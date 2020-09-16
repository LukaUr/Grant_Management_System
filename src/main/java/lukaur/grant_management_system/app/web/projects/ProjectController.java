package lukaur.grant_management_system.app.web.projects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lukaur.grant_management_system.app.web.dictionaries.IndicatorService;
import lukaur.grant_management_system.app.web.model.dictionaries.Indicator;
import lukaur.grant_management_system.app.web.model.project.Project;
import lukaur.grant_management_system.app.web.model.project.applicant.LegalEntity;
import lukaur.grant_management_system.app.web.model.project.misc.ConsentOption;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectsService projectsService;
    private final IndicatorService indicatorService;

    @ModelAttribute("indicatorList")
    private List<Indicator> indicatorList() {
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

    @ModelAttribute("consentOptions")
    private List<ConsentOption> consentOptions() {
        return Arrays.asList(ConsentOption.values().clone());
    }

    @GetMapping("/project")
    public String showProject(@RequestParam Long id, Model model) {
        Project project = projectsService.findById(id);
        model.addAttribute("project", project);
        return "project/project";
    }

    @PostMapping("/save")
    public String processProjectSave(@Valid Project project,
                                     BindingResult result,
                                     @RequestParam Map<String, String> allParams
                                     ) {
        allParams.forEach((k, v) -> System.out.println(k + ": " + v));
        if (result.hasErrors()) {
            log.error("Cant save a project");
            return "project/project";
        }
        log.info("Attempting to save project");
        projectsService.save(project);
        log.info("Project saved");
        return "redirect:/menu";
    }

    @GetMapping("/test")
    public String test() {
        log.info("test started");
        List<LegalEntity> partners = projectsService.getPartners(3L);
        partners.forEach(p -> log.info(String.valueOf(p)));
        return "redirect:/menu";
    }
}

