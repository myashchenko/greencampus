package ua.greencampus.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ivan Mikho, created on 19.05.16.
 */

@Controller
@RequestMapping("/theme/{courseId}")
public class ThemeController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll() {
        return "themes";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById() { return "theme"; }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUser(@PathVariable Long courseId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("message", "create");
        return "themeCREATE";
    }
}
