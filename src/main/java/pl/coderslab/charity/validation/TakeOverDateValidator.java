package pl.coderslab.charity.validation;

import pl.coderslab.charity.model.DonationDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class TakeOverDateValidator implements ConstraintValidator<TakeOverDate, Object> {

    @Override
    public void initialize(TakeOverDate constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        DonationDTO donationDTO = (DonationDTO) o;
        if(donationDTO.isPickedUp()) {
            if (donationDTO.getCreated() == null) {
                return donationDTO.getTakeOverDate().isBefore(LocalDate.now().plusDays(1));
            }
            return donationDTO.getTakeOverDate().isAfter(donationDTO.getCreated().minusDays(1))
                    && donationDTO.getTakeOverDate().isBefore(LocalDate.now().plusDays(1));
        }
        return true;
    }
}
