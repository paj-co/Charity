package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private InstitutionRepository institutionRepository;

    @Autowired
    public AdminController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @ModelAttribute("institutions")
    public List<Institution> institutionList(){
        return institutionRepository.findAll();
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






}
