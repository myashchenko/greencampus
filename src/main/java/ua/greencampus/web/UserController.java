package ua.greencampus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.greencampus.dto.UserDTO;
import ua.greencampus.service.AuthenticationService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Nikolay Yashchenko
 */
@Controller
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("userid", id);
        return "user";
    }

    @RequestMapping(value = "/user/account", method = RequestMethod.GET)
    public String getUser(Model model) {
        Long id = authenticationService.getLoggedInUserId();
        model.addAttribute("userid", id);
        return "userREAD";
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("message", "create");
        return "userCREATE";
    }


    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userid", id);
        model.addAttribute("message", "update");
        return "userUpdateAdmin";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.GET)
    public String updateForUser(Model model) {
        Long id = authenticationService.getLoggedInUserId();
        model.addAttribute("userid", id);
        model.addAttribute("message", "update");
        return "userUPDATE";
    }

    @RequestMapping(value = "/user/update/pass", method = RequestMethod.GET)
    public String updatePassword(Model model) {
        Long id = authenticationService.getLoggedInUserId();
        model.addAttribute("userid", id);
        model.addAttribute("message", "update");
        return "userPassword";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAll() {
        return "users";
    }

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userid", id);
        return "userDELETE";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, @RequestParam(name = "redirect", required = false) String redirect, Model model) {
        request.getSession().setAttribute("url_redirect_login", redirect);
        model.addAttribute("userDTO", new UserDTO());
        return "login";
    }
}
