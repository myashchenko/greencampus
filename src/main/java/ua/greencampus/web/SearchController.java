package ua.greencampus.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Nikolay Yashchenko
 */
@Controller
public class SearchController {

    @GetMapping(value = "/search")
    public String search() {
        return "search";
    }
}
