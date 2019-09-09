package pl.coderslab.charity.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoDateWhenCheckboxIsCheckedValidator implements ConstraintValidator<NoDateWhenCheckboxIsChecked, Object> {

    private String isChecked;

    private String actualPickedUpDate;

    @Override
    public void initialize(NoDateWhenCheckboxIsChecked constraintAnnotation) {
        this.isChecked = constraintAnnotation.isChecked();
        this.actualPickedUpDate = constraintAnnotation.actualPickedUpDate();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Boolean checkbox = (Boolean) new BeanWrapperImpl(o).getPropertyValue(isChecked);
        Object date = new BeanWrapperImpl(o).getPropertyValue(actualPickedUpDate);
        if(!checkbox && date != null) return false;
        return true;
    }
}
