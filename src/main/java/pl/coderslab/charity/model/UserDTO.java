package pl.coderslab.charity.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.validation.PasswordMatches;
import pl.coderslab.charity.validation.ValidationGroupChangeUserData;
import pl.coderslab.charity.validation.ValidationGroupChangeUserPassword;
import pl.coderslab.charity.validation.ValidationGroupRegisterUser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@PasswordMatches(groups = {ValidationGroupRegisterUser.class, ValidationGroupChangeUserPassword.class},
        message = "Powtórzone hasło różni się od pierwszego.")
@Component
public @Data class UserDTO {

    private Long id;

    @NotBlank(groups = {ValidationGroupRegisterUser.class, ValidationGroupChangeUserData.class})
    @Email(groups = {ValidationGroupRegisterUser.class, ValidationGroupChangeUserData.class})
    private String email;

    @NotBlank(groups = {ValidationGroupRegisterUser.class, ValidationGroupChangeUserData.class})
    private String firstName;
    @NotBlank(groups = {ValidationGroupRegisterUser.class, ValidationGroupChangeUserData.class})
    private String lastName;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*?])[a-zA-Z0-9!@#$%^&*?]{8,20}$",
            groups = {ValidationGroupRegisterUser.class, ValidationGroupChangeUserPassword.class},
            message = "Czy hasło zawiera wielkie i małe litery, cyfry i znaki specjalne? Powinno mieć od 8 - 20 znaków.")
    private String password;

    private String matchingPassword;
    private String oldPassword;

    private boolean enabled;

    private Set<Role> roles;

    private List<Integer> rolesIdList;

}
