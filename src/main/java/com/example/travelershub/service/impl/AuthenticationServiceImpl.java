package com.example.travelershub.service.impl;

import com.example.travelershub.model.User;
import com.example.travelershub.model.enumfolder.RoleName;
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
    public User register(User userDto) {
        User user = new User();
        if (userService.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("User with email " + userDto.getEmail() + " is already exist");
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setTelephone(userDto.getTelephone());
        user.setPassword(userDto.getPassword());
        user.setRoles(Set.of(roleService.getByName(RoleName.USER)));
        userService.save(user);
        return user;
    }
}
