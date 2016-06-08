package ua.greencampus.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Nikolay Yashchenko
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll() {
        return "courses";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById() { return "course"; }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("message", "create");
        return "courseCREATE";
    }
}

