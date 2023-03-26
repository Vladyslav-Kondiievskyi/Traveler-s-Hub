package com.example.travelershub.service.impl;

import com.example.travelershub.model.Role;
import com.example.travelershub.model.User;
import com.example.travelershub.service.AuthenticationService;
import com.example.travelershub.service.RoleService;
import com.example.travelershub.service.UserService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final RoleService roleService;
    private final UserService userService;

    public AuthenticationServiceImpl(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public User register(String email, String password,
                         String telephone, String firstName, String lastName) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoles(Set.of(roleService.getByName(Role.RoleName.USER)));
        userService.save(user);
        return null;
    }
}
