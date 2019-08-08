package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.model.UserDTO;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validation.ValidationGroupChangeUserData;
import pl.coderslab.charity.validation.ValidationGroupChangeUserPassword;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public UserController( UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                           UserService userService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String userProfile() {
        return "user/userProfile";
    }

    @GetMapping("/update/{userId}")
    public String userUpdate(@PathVariable long userId, Model model) {
        Optional<User> adminOptional = userRepository.findById(userId);
        if(adminOptional.isPresent()) {
            User user = adminOptional.get();
//            List<Role> allRoles = roleRepository.findAll();
//            allRoles.removeAll(user.getRoles());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
//            if(user.getEnabled() == 1) {
//                userDTO.setEnabled(true);
//            } else {
//                userDTO.setEnabled(false);
//            }
//            userDTO.setRoles(user.getRoles());

//            model.addAttribute("remainingRoles", allRoles);
            model.addAttribute("userDTO", userDTO);
            return "user/userUpdateForm";
        }
        return "redirect:/user/profile";
    }

    @PostMapping("/update/{userId}")
    public String userUpdate(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable long userId,
                             @ModelAttribute @Validated({ValidationGroupChangeUserData.class}) UserDTO userDTO,
                             BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "user/userUpdateForm";
        }
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        if(userOptional.isPresent()) {

            User user = currentUser.getUser();

            if(!userDTO.getEmail().equals(user.getEmail())) {
                if(userService.checkIfEmailIsInDatabase(userDTO.getEmail())) {
                    model.addAttribute("emailAlreadyExistsError", "Na ten adres e-mail założono już konto");
                    return "user/userUpdateForm";
                }
            }

            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
//            if(userDTO.isEnabled()) {
//                user.setEnabled(1);
//            } else {
//                user.setEnabled(0);
//            }
//            Set<Role> roles = new HashSet<>();
//            for(int roleId : userDTO.getRolesIdList()) {
//                Optional<Role> role = roleRepository.findById(roleId);
//                if(role.isPresent()) {
//                    roles.add(role.get());
//                }
//            }
//            user.setRoles(roles);
            userRepository.save(user);
            return "redirect:/user/profile";
        }
        return "redirect:/user/profile";
    }

    @GetMapping("/update/password/{userId}")
    public String userUpdatePassword(@PathVariable long userId, Model model) {
        Optional<User> adminOptional = userRepository.findById(userId);
        if(adminOptional.isPresent()) {
            User user = adminOptional.get();

            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());

            model.addAttribute("userDTO", userDTO);
            return "user/userUpdatePasswordForm";
        }
        return "redirect:/user/profile";
    }

    @PostMapping("/update/password/{userId}")
    public String userUpdatePassword(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable long userId,
                                     @ModelAttribute @Validated({ValidationGroupChangeUserPassword.class}) UserDTO userDTO,
                                     BindingResult bindingResult, Model model) {

        User user = currentUser.getUser();
        if(bindingResult.hasErrors()) {
            if(!BCrypt.checkpw(userDTO.getOldPassword(), user.getPassword())) {
                model.addAttribute("oldPasswordError", "Hasło jest nie poprawne");
            }
            return "user/userUpdatePasswordForm";
        }
        if(!BCrypt.checkpw(userDTO.getOldPassword(), user.getPassword())) {
            model.addAttribute("oldPasswordError", "Hasło jest nie poprawne");
            return "user/userUpdatePasswordForm";
        }
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        if(userOptional.isPresent()) {

            if(userDTO.getPassword().length() > 0 && userDTO.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            userRepository.save(user);
            return "redirect:/user/profile";
        }
        return "redirect:/user/profile";
    }

}
