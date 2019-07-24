package pl.coderslab.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        if(bagQuantity == null || bagQuantity == 0) {
            return 0L;
        }
        return bagQuantity;
    }

    @ModelAttribute("supportedInstitutions")
    public int supportedInstitutions(){
        List<Institution> institutionList = donationRepository.sumSupportedInstitutions();
        if (institutionList == null || institutionList.size() == 0) {
            return 0;
        }
        return institutionList.size();
    }



    @RequestMapping("/")
    public String homeAction(Model model){
        return "index";
    }


}
