package com.example.travelershub.service;

import com.example.travelershub.model.User;
import java.util.Optional;

public interface UserService {
    User save(User user);

    User get(Long id);

    Optional<User> findByEmail(String email);

    User delete(Long id);
}
