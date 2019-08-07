package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin/institution")
public class InstitutionController {

    private InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/add")
    public String institutionAdd(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/adminInstitutionForm";
    }

    @PostMapping("/add")
    public String institutionAdd(@ModelAttribute @Valid Institution institution, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "admin/adminInstitutionForm";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/update/{institutionId}")
    public String institutionUpdate(@PathVariable long institutionId, Model model) {
        Optional<Institution> institution = institutionRepository.findById(institutionId);
        if(institution.isPresent()) {
            model.addAttribute("institution", institution.get());
            return "admin/adminInstitutionFormUpdate";
        }
        return "redirect:admin/404";
    }

    @PostMapping("/update/{institutionId}")
    public String institutionUpdate(@PathVariable long institutionId, @ModelAttribute @Valid Institution institution, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "admin/adminInstitutionFormUpdate";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/delete/confirm/{institutionId}")
    public String institutionDeleteConfirm(@PathVariable long institutionId, Model model) {
        Optional<Institution> institution = institutionRepository.findById(institutionId);
        if(institution.isPresent()) {
            model.addAttribute("institution", institution.get());
            return "admin/adminInstitutionConfirmDelete";
        }
        return "redirect:admin/404";
    }

    @GetMapping("/delete/{institutionId}")
    public String institutionDelete(@PathVariable long institutionId) {
        Optional<Institution> institution = institutionRepository.findById(institutionId);
        if(institution.isPresent()) {
            institutionRepository.delete(institution.get());
            return "redirect:/admin/institutions";
        }
        return "redirect:admin/404";
    }




}
