package ua.greencampus.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ivan Mikho, created on 19.05.16.
 */

@Controller
@RequestMapping("/theme/{courseId}")
public class ThemeController {

    @GetMapping(value = "/all")
    public String getAll() {
        return "themes";
    }

    @GetMapping(value = "/{id}")
    public String getById() {
        return "theme";
    }

    @GetMapping(value = "/create")
    public String createUser(@PathVariable Long courseId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("message", "create");
        return "themeCreate";
    }
}
