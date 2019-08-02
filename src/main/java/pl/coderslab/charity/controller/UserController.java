package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String userRegister(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/userRegister";
    }

    @PostMapping("/register")
    public String userRegister(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            userService.passwordEqPassword2(user, model);
            return "user/userRegister";
        }
        if(!userService.passwordEqPassword2(user, model)) {
            return "user/userRegister";
        }
        userService.saveUser(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String userLogin(Model model) {
        model.addAttribute("user", new User());
        return "user/userLogin";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/userLogin";
        }
        User userFromDb = userService.findUserByEmail(user.getEmail());
        if(userFromDb != null) {
            if(BCrypt.checkpw(user.getPassword(), userFromDb.getPassword())) {
                return "redirect:/donation";
            }
        }
        model.addAttribute("loginError", "Email lub hasło są nieprawidłowe");
        return "user/userLogin";
    }

    @GetMapping("/logout")
    public String userLogout() {
        //TODO logout
        return "/";
    }
}
