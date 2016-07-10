package ua.greencampus.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Nikolay Yashchenko
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @GetMapping(value = "/all")
    public String getAll() {
        return "courses";
    }

    @GetMapping(value = "/{id}")
    public String getById() {
        return "course";
    }

    @GetMapping(value = "/create")
    public String createUser(Model model) {
        model.addAttribute("message", "create");
        return "courseCreate";
    }
}

