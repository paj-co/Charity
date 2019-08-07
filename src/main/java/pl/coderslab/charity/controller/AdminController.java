package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.NewAdmins;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private InstitutionRepository institutionRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserService userService;

    @Autowired
    public AdminController(InstitutionRepository institutionRepository, UserRepository userRepository,
                           RoleRepository roleRepository, UserService userService) {
        this.institutionRepository = institutionRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
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
            if(newAdminOptional.isPresent()) {
                userService.addAdminRole(newAdminOptional.get());
            }
        }
        return "redirect:/admin/list";
    }








}
