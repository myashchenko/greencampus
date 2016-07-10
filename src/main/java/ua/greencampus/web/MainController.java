package ua.greencampus.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Arsenii on 21.03.2016.
 */
@Controller
public class MainController {

    @GetMapping(value = {"/", "/home"})
    public String homePage() {
        return "home";
    }

    @GetMapping(value = "/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping(value = "/access-denied")
    public String accessDenied() {
        return "accessDenied";
    }
}
