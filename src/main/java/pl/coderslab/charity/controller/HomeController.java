package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private DonationRepository donationRepository;

    @ModelAttribute("institutions")
    public List<Institution> institutionList(){
        return institutionRepository.findAll();
    }

    @ModelAttribute("handOverBags")
    public Long handOverBags(){
        Long bagQuantity = donationRepository.sumHandOverBags();
        if(bagQuantity == null) {
            return 0L;
        }
        return bagQuantity;
    }

    @ModelAttribute("supportedInstitutions")
    public Long numberOfSupportedInstitutions(){
        Long numberOfSupportedInstitutions = donationRepository.sumSupportedInstitutions();
        if (numberOfSupportedInstitutions == null) {
            return 0L;
        }
        return numberOfSupportedInstitutions;
    }

    @RequestMapping("/")
    public String homeAction(){
        return "index";
    }


}
