package pl.coderslab.charity.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.validation.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordMatches
@Component
public @Data class UserDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @Size(min = 3, max = 10)
    private String password;

    private String matchingPassword;

}
