package com.example.travelershub.service.impl;

import com.example.travelershub.model.User;
import com.example.travelershub.repository.UserRepository;
import com.example.travelershub.service.UserService;
import java.util.Optional;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    // todo need field of PasswordEncoder
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findUserByEmail(email));
    }

    @Override
    public User delete(Long id) {
        return userRepository.deleteUserById(id);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findUserByFirstName(name);
    }
}
