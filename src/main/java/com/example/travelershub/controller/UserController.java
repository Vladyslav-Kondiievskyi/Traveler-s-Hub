package com.example.travelershub.controller;

import com.example.travelershub.dto.response.UserResponseDto;
import com.example.travelershub.model.User;
import com.example.travelershub.service.UserService;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UserController {
    private final UserService userService;
    private final ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper;

    public UserController(UserService userService,
                          ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper) {
        this.userService = userService;
        this.userResponseDtoMapper = userResponseDtoMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto findByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User with email " + email + " not found"));
        return userResponseDtoMapper.mapToDto(user);
    }
}
