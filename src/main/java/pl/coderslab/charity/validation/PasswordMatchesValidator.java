package pl.coderslab.charity.validation;

import pl.coderslab.charity.model.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        UserDTO userDTO = (UserDTO) object;
        return userDTO.getPassword().equals(userDTO.getMatchingPassword());
    }

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }
}
