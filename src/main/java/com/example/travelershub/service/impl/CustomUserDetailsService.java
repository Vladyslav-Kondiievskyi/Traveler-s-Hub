package com.example.travelershub.service.impl;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.example.travelershub.model.User;
import com.example.travelershub.service.UserService;
import java.util.Optional;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> userOptional = userService.findByEmail(email);
        UserBuilder userBuilder;
        if (userOptional.isPresent()) {
            userBuilder = withUsername(email);
            User user = userOptional.get();
            userBuilder.password(user.getPassword());
            userBuilder.roles(user.getRoles()
                           .stream()
                           .map(x -> x.getRoleName().name())
                           .toArray(String[]::new));
            return userBuilder.build();
        }
        throw new UsernameNotFoundException("User not found.");
    }
}
