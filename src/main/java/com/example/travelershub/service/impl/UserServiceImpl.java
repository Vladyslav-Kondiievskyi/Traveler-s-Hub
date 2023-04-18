package com.example.travelershub.service.impl;

import com.example.travelershub.config.PasswordEncoderProvider;
import com.example.travelershub.model.User;
import com.example.travelershub.repository.UserRepository;
import com.example.travelershub.service.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoderProvider encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoderProvider encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.getPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findUserByEmail(email));
    }
}
