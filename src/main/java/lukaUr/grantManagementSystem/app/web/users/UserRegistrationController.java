package lukaUr.grantManagementSystem.app.web.users;

import lombok.RequiredArgsConstructor;
import lukaUr.grantManagementSystem.app.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@Valid User user) {
        userService.registerUser(user);
        return "redirect:/register";
    }
}