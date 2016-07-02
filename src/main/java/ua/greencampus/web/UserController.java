package ua.greencampus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ua.greencampus.dto.UserDto;
import ua.greencampus.service.AuthenticationService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Nikolay Yashchenko
 */
@Controller
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping(value = "/user/{id}")
    public String getById(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("userid", id);
        return "user";
    }

    @GetMapping(value = "/user/account")
    public String getUser(Model model) {
        Long id = authenticationService.getLoggedInUserId();
        model.addAttribute("userid", id);
        return "userREAD";
    }

    @GetMapping(value = "/user/create")
    public String createUser(Model model) {
        model.addAttribute("message", "create");
        return "userCREATE";
    }


    @GetMapping(value = "/user/update/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userid", id);
        model.addAttribute("message", "update");
        return "userUpdateAdmin";
    }

    @GetMapping(value = "/user/update")
    public String updateForUser(Model model) {
        Long id = authenticationService.getLoggedInUserId();
        model.addAttribute("userid", id);
        model.addAttribute("message", "update");
        return "userUPDATE";
    }

    @GetMapping(value = "/user/update/pass")
    public String updatePassword(Model model) {
        Long id = authenticationService.getLoggedInUserId();
        model.addAttribute("userid", id);
        model.addAttribute("message", "update");
        return "userPassword";
    }

    @GetMapping(value = "/users")
    public String getAll() {
        return "users";
    }

    @GetMapping(value = "/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userid", id);
        return "userDELETE";
    }

    @GetMapping(value = "/login")
    public String loginPage(@RequestParam(name = "redirect", required = false) String redirect,
                            HttpServletRequest request, Model model) {
        request.getSession().setAttribute("url_redirect_login", redirect);
        model.addAttribute("userDto", new UserDto());
        return "login";
    }
}
