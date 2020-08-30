package lukaUr.grantManagementSystem.app.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {

    @GetMapping
    public String showBasicWelcome() {
        return "Hello!!!";
    }
}
