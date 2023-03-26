package com.example.travelershub.service;

import com.example.travelershub.model.User;

// todo in Q&A how correct create register if we can other field like telephone and other
public interface AuthenticationService {
    User register(String email,
                  String password,
                  String telephone,
                  String firstName,
                  String lastName);
}
