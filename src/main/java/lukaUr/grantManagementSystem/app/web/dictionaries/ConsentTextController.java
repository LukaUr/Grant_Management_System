package lukaUr.grantManagementSystem.app.web.dictionaries;

import lombok.RequiredArgsConstructor;
import lukaUr.grantManagementSystem.app.web.model.dictionaries.ConsentText;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/dictionaries/consent")
@RequiredArgsConstructor
public class ConsentTextController {

    private final ConsentTextService consentTextService;


    @GetMapping("/show")
    public String showFullConsentList(Model model) {
        List<ConsentText> consents = consentTextService.findAll();
        model.addAttribute("consents", consents);
        return "dictionary/allConsents";
    }

    @GetMapping("/add")
    public String showAddConsentTextForm(Model model) {
        ConsentText consentText = new ConsentText();
        model.addAttribute("consentText", consentText);
        return "dictionary/addConsent";
    }

    @PostMapping("/add")
    public String processAddConsentTextForm(@Valid ConsentText consentText, BindingResult result) {
        if (result.hasErrors()) {
            return "dictionary/addConsent";
        }
        consentTextService.add(consentText);
        return "redirect:/dictionaries/consent/show";
    }

    @GetMapping("/delete")
    public String deleteConsent(@RequestParam Long id, Model model) {
        ConsentText consentText = consentTextService.find(id);
        boolean success = consentTextService.delete(consentText);
        if (!success) {
            model.addAttribute("message", "This consent can not be deleted. Probably in use.");
            model.addAttribute("link", "/dictionaries/consent/show");
            return "errorPage";
        }
        return "redirect:/dictionaries/consent/show";
    }

    @GetMapping("/edit")
    public String showEditConsentForm(@RequestParam Long id, Model model) {
        ConsentText consentText = consentTextService.find(id);
        model.addAttribute("consentText", consentText);
        return "dictionary/editConsent";
    }

    @PostMapping("/edit")
    public String processEditConsentForm(@Valid ConsentText consentText, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "dictionary/editConsent";
        }
        boolean success = consentTextService.update(consentText);
        if (!success) {
            model.addAttribute("message", "This consent can not be edited.");
            model.addAttribute("link", "/dictionaries/consent/show");
            return "errorPage";
        }
        return "redirect:/dictionaries/consent/show";
    }
}
