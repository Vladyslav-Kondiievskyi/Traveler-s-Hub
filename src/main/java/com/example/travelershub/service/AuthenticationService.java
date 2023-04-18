package com.example.travelershub.service;

import com.example.travelershub.model.User;

public interface AuthenticationService {
    User register(User userDto);
}
