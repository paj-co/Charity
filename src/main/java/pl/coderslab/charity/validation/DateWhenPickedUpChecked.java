package pl.coderslab.charity.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateWhenPickedUpCheckedValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateWhenPickedUpChecked {

    String message() default "You should provide date when checkbox is checked";

    String isChecked();

    String actualPickedUpDate();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
