package pl.coderslab.charity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findRoleByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public boolean passwordEqPassword2(User user, Model model) {
        boolean isEq = user.getPassword().equals(user.getMatchingPassword());
        if(!isEq) {
            model.addAttribute("pass1NotEqPass2", "Hasła nie są równe!");
        }
        return isEq;
    }

    @Override
    public void addAdminRole(User user) {
        Role adminRole = roleRepository.findRoleByName("ROLE_ADMIN");
        Set<Role> roles;
        if(user.getRoles().size() == 0) {
            user.setRoles(new HashSet<>(Arrays.asList(adminRole)));
        } else {
            roles = user.getRoles();
            roles.add(adminRole);
            user.setRoles(roles);
        }
        userRepository.save(user);
    }

    @Override
    public boolean checkIfEmailIsInDatabase(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}
