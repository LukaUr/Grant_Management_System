package lukaUr.grantManagementSystem.app.web.projects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/project")
public class ProjectController {

    @GetMapping("/project")
    public String showProject() {
        return "project/project";
    }
}
