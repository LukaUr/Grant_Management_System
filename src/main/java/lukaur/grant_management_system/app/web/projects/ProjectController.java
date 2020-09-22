package lukaur.grant_management_system.app.web.projects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lukaur.grant_management_system.app.web.dictionaries.IndicatorService;
import lukaur.grant_management_system.app.web.model.dictionaries.Indicator;
import lukaur.grant_management_system.app.web.model.project.Project;
import lukaur.grant_management_system.app.web.model.project.misc.ConsentOption;
import lukaur.grant_management_system.app.web.model.project.timetable.Task;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
        List<Integer> yearsOfProject = determineYears(project);  // the budget table requires to determine the project span
        model.addAttribute("yearsOfProject", yearsOfProject);
        return "project/project";
    }


    @PostMapping("/save")
    public String processProjectSave(@Valid Project project,
                                     BindingResult result,
                                     @RequestParam Map<String, String> allParams) {
        allParams.forEach((k, v) -> System.out.println(k + ": " + v));
        addErrorsOnTasks(project, result);    //hibernate can't bind automatically errors with tasks, must be added manually
        if (result.hasErrors()) {
            log.error("Cant save the project");
            result.getAllErrors().forEach(System.out::println);
            addInformationOnIndicatorType(project);      //the information on Indicator.Type is lost, must be added manualy
            return "project/project";
        }
        log.warn("Attempting to save project");
        projectsService.save(project);
        log.warn("Project saved");
        return "redirect:/menu";
    }


    private void addInformationOnIndicatorType(Project project) {
        project.getIndicators()
                .forEach(i -> {
                    i.setIndicator(indicatorService.find(i.getIndicator().getId()));
                });
    }

    private void addErrorsOnTasks(Project project, BindingResult result) {
        System.out.println("----------addErrorsOntasks------------------");
        project.getTimetable().getTasks()
                .stream()
                .peek(System.out::println)
                .filter(t -> !(t.getId() == null && t.getName() == null && t.getTaskStart() == null & t.getTaskEnd() == null))
                .peek(System.out::println)
                .filter(t ->
                        t.getTaskStart() == null || t.getTaskEnd() == null || t.getTaskStart().after(t.getTaskEnd())
                )
                .peek(System.out::println)
                .forEach(t -> result.addError(new ObjectError(
                        t.getClass().toString(), "For each task in timetable: start date must be before the end date")));
    }

    private List<Integer> determineYears(Project project) {
        if (project.getTimetable() == null) {
            return null;
        }
        List<Task> tasks = project.getTimetable().getTasks();
        Optional<Integer> firstYear = tasks
                .stream()
                .map(Task::getTaskStart)
                .map(Date::toLocalDate)
                .map(LocalDate::getYear)
                .min((a, b) -> a - b);
        Optional<Integer> lastYear = tasks
                .stream()
                .map(Task::getTaskEnd)
                .map(Date::toLocalDate)
                .map(LocalDate::getYear)
                .max((a, b) -> a - b);
        if (firstYear.isPresent() && lastYear.isPresent()) {
            return (List<Integer>) IntStream
                    .range(firstYear.get(), lastYear.get() + 1)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return null;
    }

}

