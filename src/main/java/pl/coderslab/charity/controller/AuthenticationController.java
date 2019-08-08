package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.UserDTO;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validation.ValidationGroupRegisterUser;

@Controller
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String userRegister(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "user/userRegister";
    }

    @PostMapping("/register")
    public String userRegister(@ModelAttribute @Validated({ValidationGroupRegisterUser.class}) UserDTO userDTO, BindingResult bindingResult, Model model) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setMatchingPassword(userDTO.getMatchingPassword());
        if (bindingResult.hasErrors()) {
//            userService.passwordEqPassword2(user, model);
            return "user/userRegister";
        }
//        if(!userService.passwordEqPassword2(user, model)) {
//            return "user/userRegister";
//        }
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String userLogin(@RequestParam(required = false) String error, Model model) {
        if(error != null && error.equals("true")) {
            model.addAttribute("loginError", "Błędny e-mail lub hasło");
        }
        return "user/userLogin";
    }

}
