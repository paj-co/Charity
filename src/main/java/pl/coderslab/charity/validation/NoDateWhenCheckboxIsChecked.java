package pl.coderslab.charity.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NoDateWhenCheckboxIsCheckedValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoDateWhenCheckboxIsChecked  {

    String message() default "Clear date field when checkbox is unchecked";

    String isChecked();

    String actualPickedUpDate();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
