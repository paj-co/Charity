package pl.coderslab.charity.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateWhenPickedUpCheckedValidator implements ConstraintValidator<DateWhenPickedUpChecked, Object> {

    private String isChecked;

    private String actualPickedUpDate;

    @Override
    public void initialize(DateWhenPickedUpChecked constraintAnnotation) {
        this.isChecked = constraintAnnotation.isChecked();
        this.actualPickedUpDate = constraintAnnotation.actualPickedUpDate();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        boolean checkbox = (Boolean) (new BeanWrapperImpl(o).getPropertyValue(isChecked));
        Object date = new BeanWrapperImpl(o).getPropertyValue(actualPickedUpDate);
        if(checkbox && date == null) return false;
        return true;
    }
}
