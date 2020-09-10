package lukaur.grant_management_system.app.web.projects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lukaur.grant_management_system.app.web.calls.CallForProjectsService;
import lukaur.grant_management_system.app.web.model.CallForProjects;
import lukaur.grant_management_system.app.web.model.User;
import lukaur.grant_management_system.app.web.model.dictionaries.ConsentText;
import lukaur.grant_management_system.app.web.model.project.Project;
import lukaur.grant_management_system.app.web.model.project.misc.Consent;
import lukaur.grant_management_system.app.web.users.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

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
//        set user
        String userName = principal.getName();
        User user = userService.findByName(userName);
        project.setUser(user);
//        set consents
        Set<Consent> consentSet = callForProjectsService
                .find(project.getCallForProjects().getId()).getConsentSet()
                .stream()
                .peek(System.out::println)
                .map(c -> {
                    Consent consent = new Consent();
                    consent.setConsentText(c);
                    return consent;
                })
                .peek(System.out::println)
                .collect(Collectors.toSet());

        System.out.println(consentSet.size());
        consentSet.forEach(System.out::println);
        project.setConsents(consentSet);
//        save project
        Project saved = projectsService.create(project);
        if (saved != null) {
            return "redirect:/project/project?id=" + saved.getId();
        }
        log.error("Can't save");
        model.addAttribute("message", "Can not create new project");
        return "project/initialProject";
    }
}
