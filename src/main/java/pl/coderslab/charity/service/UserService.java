package pl.coderslab.charity.service;

import org.springframework.ui.Model;
import pl.coderslab.charity.entity.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    void addAdminRole(User user);

    public boolean passwordEqPassword2(User user, Model model);

    public boolean checkIfEmailIsInDatabase(String email);

}
