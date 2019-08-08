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
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@PasswordMatches(groups = {ValidationGroupRegisterUser.class, ValidationGroupChangeUserPassword.class})
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

    @Size(min = 3, max = 10, groups = {ValidationGroupRegisterUser.class, ValidationGroupChangeUserPassword.class})
    private String password;

    private String matchingPassword;
    private String oldPassword;

    private boolean enabled;

    private Set<Role> roles;

    private List<Integer> rolesIdList;

}
