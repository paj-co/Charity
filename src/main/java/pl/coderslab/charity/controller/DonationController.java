package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private InstitutionRepository institutionRepository;
    private CategoryRepository categoryRepository;
    private DonationRepository donationRepository;

    @Autowired
    public DonationController(InstitutionRepository institutionRepository, CategoryRepository categoryRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
    }

    @ModelAttribute("institutions")
    public List<Institution> institutionList(){
        return institutionRepository.findInstitutionsByActivePartnershipOrderByName(true);
    }

    @ModelAttribute("categories")
    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }

    @GetMapping("")
    public String donationsForm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        Donation donation = new Donation();
        donation.setUser(currentUser.getUser());
        model.addAttribute("donation", donation);
        return "form";
    }

    @PostMapping("")
    public String donationsForm(@ModelAttribute @Valid Donation donation, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "W formularzu są błędy! Upewnij się, że wszystkie pola zostały poprawnie wypełnione!");
            if(donation.getCategories() != null && donation.getCategories().size() > 0) {
                model.addAttribute("againCheckCategory", "Ponownie zaznacz kategorię");
            }
            return "form";
        }
        donationRepository.save(donation);
        return "form-confirmation";
    }
}
