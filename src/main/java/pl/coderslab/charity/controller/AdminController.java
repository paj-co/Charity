package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private InstitutionRepository institutionRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AdminController(InstitutionRepository institutionRepository, UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.institutionRepository = institutionRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @ModelAttribute("institutions")
    public List<Institution> institutionList(){
        return institutionRepository.findAll();
    }

    @ModelAttribute("admins")
    public List<User> admins(){
        Optional<Role> roleAdmin = roleRepository.findById(2);
        return userRepository.findUsersByRoles(roleAdmin.orElse(null));
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








}
