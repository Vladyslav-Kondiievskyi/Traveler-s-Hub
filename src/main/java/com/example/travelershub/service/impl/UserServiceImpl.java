package com.example.travelershub.service.impl;

import java.util.Optional;
import com.example.travelershub.model.User;
import com.example.travelershub.repository.UserRepository;
import com.example.travelershub.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    // todo need field of PasswordEncoder
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        // todo in future need to check passwordEncoder
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(userRepository.findAllByEmail(email));
    }
}
