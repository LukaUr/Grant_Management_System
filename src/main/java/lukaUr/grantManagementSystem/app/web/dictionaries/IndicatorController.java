package lukaUr.grantManagementSystem.app.web.dictionaries;

import lombok.RequiredArgsConstructor;
import lukaUr.grantManagementSystem.app.web.model.dictionaries.Indicator;
import lukaUr.grantManagementSystem.app.web.model.dictionaries.IndicatorType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/dictionaries/indicators")
@RequiredArgsConstructor
public class IndicatorController {

    private final IndicatorService indicatorService;

    @GetMapping("/show")
    public String showAllIndicators(Model model) {
        List<Indicator> indicators = indicatorService.findAll();
        model.addAttribute("indicators", indicators);
        return "dictionary/allIndicators";
    }

    @GetMapping("/add")
    public String showAddIndicatortForm(Model model) {
        Indicator indicator = new Indicator();
        model.addAttribute("indicator", indicator);
        model.addAttribute("types", Arrays.asList(IndicatorType.values()));
        return "dictionary/addIndicator";
    }

    @PostMapping("/add")
    public String processAddIndicatorForm(@Valid Indicator indicator, BindingResult result) {
        if (result.hasErrors()) {
            return "dictionary/addIndicator";
        }
        indicatorService.add(indicator);
        return "redirect:/dictionaries/indicators/show";
    }

    @GetMapping("/delete")
    public String deleteIndicator(@RequestParam Long id, Model model) {
        Indicator indicator = indicatorService.find(id);
        boolean success = indicatorService.delete(indicator);
        if (!success) {
            model.addAttribute("message", "This indicator can not be deleted. Probably in use.");
            model.addAttribute("link", "/dictionaries/indicators/show");
            return "errorPage";
        }
        return "redirect:/dictionaries/indicators/show";
    }

    @GetMapping("/edit")
    public String showEditIndicatorForm(@RequestParam Long id, Model model) {
        Indicator indicator = indicatorService.find(id);
        model.addAttribute("indicator", indicator);
        return "dictionary/editIndicator";
    }

    @PostMapping("/edit")
    public String processEditIndicatorForm(@Valid Indicator indicator, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "dictionary/editIndicator";
        }
        boolean success = indicatorService.update(indicator);
        if (!success) {
            model.addAttribute("message", "This indicator can not be edited.");
            model.addAttribute("link", "/dictionaries/indicators/show");
            return "errorPage";
        }
        return "redirect:/dictionaries/indicators/show";
    }
}
