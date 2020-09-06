package lukaur.grant_management_system.app.web.users;

import lombok.RequiredArgsConstructor;
import lukaur.grant_management_system.app.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String processRegistrationForm(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        boolean success = userService.registerUser(user);
        if (success){
            return "redirect:/login";
        }
        model.addAttribute("message", "User with this name already exists");
        return "registration";
    }
}
