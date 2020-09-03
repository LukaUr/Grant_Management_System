package lukaUr.grantManagementSystem.app.web.calls;

import lombok.RequiredArgsConstructor;
import lukaUr.grantManagementSystem.app.web.dictionaries.ConsentTextService;
import lukaUr.grantManagementSystem.app.web.model.CallForProjects;
import lukaUr.grantManagementSystem.app.web.model.dictionaries.ConsentText;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/call")
@RequiredArgsConstructor
public class CallForProjectsController {
    private final CallForProjectsService callService;
    private final ConsentTextService consentTextService;

    @ModelAttribute("consents")
    private List<ConsentText> consents() {
        return consentTextService.findAll();
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

    @GetMapping("delete")
    public String deleteCall(@RequestParam Long id, Model model) {
        boolean success = callService.delete(id);
        if (success) {
            return "redirect:/call/show";
        }
        model.addAttribute("message", "Can not delete Call for Projects");
        model.addAttribute("link", "redirect:/call/show");
        return "errorPage";
    }
}
