package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.model.NewAdmins;
import pl.coderslab.charity.model.UserDTO;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validation.ValidationGroupChangeUserData;
import pl.coderslab.charity.validation.ValidationGroupChangeUserPassword;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private InstitutionRepository institutionRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(InstitutionRepository institutionRepository, UserRepository userRepository,
                           RoleRepository roleRepository, UserService userService, BCryptPasswordEncoder passwordEncode) {
        this.institutionRepository = institutionRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncode;
    }

    @ModelAttribute("institutions")
    public List<Institution> institutionList(){
        return institutionRepository.findAll();
    }

    @ModelAttribute("simpleUsers")
    public List<User> simpleUsers(){
        Role roleUser = roleRepository.findRoleByName("ROLE_USER");
        Role roleAdmin = roleRepository.findRoleByName("ROLE_ADMIN");
        List<User> usersWithRoleUser = userRepository.findUsersByRoles(roleUser);
        usersWithRoleUser.removeIf(user -> user.getRoles().contains(roleAdmin));
        return usersWithRoleUser;
    }

    @ModelAttribute("admins")
    public List<User> admins(){
        Role roleAdmin = roleRepository.findRoleByName("ROLE_ADMIN");
        return userRepository.findUsersByRoles(roleAdmin);
    }

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin/adminDashboard";
    }

    @GetMapping("/404")
    public String admin404() {
        return "admin/admin404";
    }

    @GetMapping("/charts")
    public String adminCharts() {
        return "admin/adminCharts";
    }

    @GetMapping("/institutions")
    public String adminInstitutions() {
        return "admin/adminInstitutions";
    }

    @GetMapping("/blank")
    public String adminBlank() {
        return "admin/adminBlank";
    }

    @GetMapping("/list")
    public String adminList() {
        return "admin/adminList";
    }

    @GetMapping("/add")
    public String adminAdd(Model model) {
        model.addAttribute("newAdmins", new NewAdmins());
        return "admin/adminAdd";
    }

    @PostMapping("/add")
    public String adminAdd(@ModelAttribute NewAdmins newAdmins) {
        for(long id : newAdmins.getUsersIdList()) {
            Optional<User> newAdminOptional = userRepository.findById(id);
            newAdminOptional.ifPresent(user -> userService.addAdminRole(user));
        }
        return "redirect:/admin/list";
    }

    @GetMapping("/update/{adminId}")
    public String adminUpdate(@PathVariable long adminId, Model model) {
        Optional<User> adminOptional = userRepository.findById(adminId);
        if(adminOptional.isPresent()) {
            User user = adminOptional.get();
            List<Role> allRoles = roleRepository.findAll();
            allRoles.removeAll(user.getRoles());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            if(user.getEnabled() == 1) {
                userDTO.setEnabled(true);
            } else {
                userDTO.setEnabled(false);
            }
            userDTO.setRoles(user.getRoles());

            model.addAttribute("remainingRoles", allRoles);
            model.addAttribute("userDTO", userDTO);
            return "admin/adminUpdateForm";
        }
        return "redirect:/admin/404";
    }

    @PostMapping("/update/{adminId}")
    public String adminUpdate(@PathVariable long adminId, @ModelAttribute @Validated({ValidationGroupChangeUserData.class}) UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "admin/adminUpdateForm";
        }
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        if(userOptional.isPresent()) {

            User user = userOptional.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            if(userDTO.isEnabled()) {
                user.setEnabled(1);
            } else {
                user.setEnabled(0);
            }
            Set<Role> roles = new HashSet<>();
            for(int roleId : userDTO.getRolesIdList()) {
                Optional<Role> role = roleRepository.findById(roleId);
                if(role.isPresent()) {
                    roles.add(role.get());
                }
            }
            user.setRoles(roles);
            userRepository.save(user);
            return "redirect:/admin/list";
        }
        return "redirect:/admin/404";
    }

    @GetMapping("/update/password/{adminId}")
    public String adminUpdatePassword(@PathVariable long adminId, Model model) {
        Optional<User> adminOptional = userRepository.findById(adminId);
        if(adminOptional.isPresent()) {
            User user = adminOptional.get();

            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());

            model.addAttribute("userDTO", userDTO);
            return "admin/adminUpdatePasswordForm";
        }
        return "redirect:/admin/404";
    }

    @PostMapping("/update/password/{adminId}")
    public String adminUpdatePassword(@PathVariable long adminId, @ModelAttribute @Validated({ValidationGroupChangeUserPassword.class}) UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "admin/adminUpdatePasswordForm";
        }
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        if(userOptional.isPresent()) {

            User user = userOptional.get();
            if(userDTO.getPassword().length() > 0 && userDTO.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            userRepository.save(user);
            return "redirect:/admin/list";
        }
        return "redirect:/admin/404";
    }

    @GetMapping("/delete/confirm/{adminId}")
    public String adminDeleteConfirm(@PathVariable long adminId, Model model) {
        Optional<User> admin = userRepository.findById(adminId);
        if(admin.isPresent()) {
            model.addAttribute("admin", admin.get());
            return "admin/adminConfirmDelete";
        }
        return "redirect:/admin/404";
    }

    @GetMapping("/delete/{adminId}")
    public String adminDelete(@PathVariable long adminId, @AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        Optional<User> admin = userRepository.findById(adminId);
        if(admin.isPresent()) {
            if(entityUser.getId().equals(admin.get().getId())) {
                return "admin/adminDeleteError";
            }
            userRepository.delete(admin.get());
            return "redirect:/admin/list";
        }
        return "redirect:/admin/404";
    }

    //User management ------------------------------------------------------------------

    @GetMapping("/user/list")
    public String adminUserList() {
        return "admin/adminUserList";
    }

    @GetMapping("/user/update/{userId}")
    public String adminUserUpdate(@PathVariable long userId, Model model) {
        Optional<User> adminOptional = userRepository.findById(userId);
        if(adminOptional.isPresent()) {
            User user = adminOptional.get();
            List<Role> allRoles = roleRepository.findAll();
            allRoles.removeAll(user.getRoles());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            if(user.getEnabled() == 1) {
                userDTO.setEnabled(true);
            } else {
                userDTO.setEnabled(false);
            }
            userDTO.setRoles(user.getRoles());

            model.addAttribute("remainingRoles", allRoles);
            model.addAttribute("userDTO", userDTO);
            return "admin/user/adminUserUpdateForm";
        }
        return "redirect:/admin/404";
    }

    @PostMapping("/user/update/{userId}")
    public String adminUserUpdate(@PathVariable long userId, @ModelAttribute @Validated({ValidationGroupChangeUserData.class}) UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "admin/user/adminUserUpdateForm";
        }
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        if(userOptional.isPresent()) {

            User user = userOptional.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            if(userDTO.isEnabled()) {
                user.setEnabled(1);
            } else {
                user.setEnabled(0);
            }
            Set<Role> roles = new HashSet<>();
            for(int roleId : userDTO.getRolesIdList()) {
                Optional<Role> role = roleRepository.findById(roleId);
                if(role.isPresent()) {
                    roles.add(role.get());
                }
            }
            user.setRoles(roles);
            userRepository.save(user);
            return "redirect:/admin/user/list";
        }
        return "redirect:/admin/404";
    }

    @GetMapping("/user/update/password/{userId}")
    public String adminUserUpdatePassword(@PathVariable long userId, Model model) {
        Optional<User> adminOptional = userRepository.findById(userId);
        if(adminOptional.isPresent()) {
            User user = adminOptional.get();

            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());

            model.addAttribute("userDTO", userDTO);
            return "admin/user/adminUserUpdatePasswordForm";
        }
        return "redirect:/admin/404";
    }

    @PostMapping("/user/update/password/{userId}")
    public String adminUserUpdatePassword(@PathVariable long userId, @ModelAttribute @Validated({ValidationGroupChangeUserPassword.class}) UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "admin/user/adminUserUpdatePasswordForm";
        }
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        if(userOptional.isPresent()) {

            User user = userOptional.get();
            if(userDTO.getPassword().length() > 0 && userDTO.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            userRepository.save(user);
            return "redirect:/admin/user/list";
        }
        return "redirect:/admin/404";
    }

    @GetMapping("/user/delete/confirm/{adminId}")
    public String adminUserDeleteConfirm(@PathVariable long adminId, Model model) {
        Optional<User> user = userRepository.findById(adminId);
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
            return "admin/user/adminUserConfirmDelete";
        }
        return "redirect:/admin/404";
    }

    @GetMapping("/user/delete/{adminId}")
    public String adminUserDelete(@PathVariable long adminId, @AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        Optional<User> admin = userRepository.findById(adminId);
        if(admin.isPresent()) {
            if(entityUser.getId().equals(admin.get().getId())) {
                return "admin/adminDeleteError";
            }
            userRepository.delete(admin.get());
            return "redirect:/admin/user/list";
        }
        return "redirect:/admin/404";
    }



}
