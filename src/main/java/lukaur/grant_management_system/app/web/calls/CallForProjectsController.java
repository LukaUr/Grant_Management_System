package lukaur.grant_management_system.app.web.calls;

import lombok.RequiredArgsConstructor;
import lukaur.grant_management_system.app.web.dictionaries.ConsentTextService;
import lukaur.grant_management_system.app.web.model.CallForProjects;
import lukaur.grant_management_system.app.web.model.dictionaries.ConsentText;
import lukaur.grant_management_system.app.web.model.project.Project;
import lukaur.grant_management_system.app.web.projects.ProjectsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/call")
@RequiredArgsConstructor
public class CallForProjectsController {
    private final CallForProjectsService callService;
    private final ConsentTextService consentTextService;
    private final ProjectsService projectsService;

    @ModelAttribute("consents")
    private List<ConsentText> consents() {
        return consentTextService.findAll();
    }

    @ModelAttribute("userName")
    private String userName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    @GetMapping("/show")
    public String showAllCalls(Model model) {
        List<CallForProjects> calls = callService.findAll();
        model.addAttribute("calls", calls);
        return "call/allCalls";
    }

    @GetMapping("/add")
    public String showAddCallForm(Model model) {
        CallForProjects call = new CallForProjects();
        model.addAttribute("call", call);
        return "call/addCall";
    }

    @PostMapping("/add")
    public String processAddCallForm(@ModelAttribute("call") @Valid CallForProjects call, BindingResult result) {
        if (result.hasErrors()) {
            return "call/addCall";
        }
        callService.add(call);
        return "redirect:/call/show";
    }

    @GetMapping("/edit")
    public String showEditCallForm(@RequestParam Long id, Model model) {
        CallForProjects call = callService.find(id);
        model.addAttribute("call", call);
        return "call/editCall";
    }

    @PostMapping("/edit")
    public String processEditCallForm(@ModelAttribute("call") @Valid CallForProjects call,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            return "call/editCall";
        }
        boolean success = callService.update(call);
        if (success) {
            return "redirect:/call/show";
        }
        model.addAttribute("message", "Call for Projects could not be updated");
        model.addAttribute("link", "redirect:/call/show");
        return "errorPage";
    }

    @GetMapping("/delete")
    public String deleteCall(@RequestParam Long id, Model model) {
        boolean success = callService.delete(id);
        if (success) {
            return "redirect:/call/show";
        }
        model.addAttribute("message", "Can not delete Call for Projects");
        model.addAttribute("link", "redirect:/call/show");
        return "errorPage";
    }

    @GetMapping("/details")
    public String details(@RequestParam Long id,
                          Model model) {
        List<Project> projects = projectsService.findAllByCallId(id);
        model.addAttribute("projects", projects);
        CallForProjects callForProjects = callService.find(id);
        model.addAttribute("call", callForProjects);
        BigDecimal requestedFunding = projects.stream()
                .map(Project::getGrantValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("requestedFunding", requestedFunding);
        return "call/details";
    }
}
