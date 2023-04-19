package com.example.travelershub.controller;

import com.example.travelershub.dto.response.UserResponseDto;
import com.example.travelershub.model.User;
import com.example.travelershub.service.RoleService;
import com.example.travelershub.service.UserService;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"*"})
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

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        User user = userService.get(id);
        return userResponseDtoMapper.mapToDto(user);
    }

    @GetMapping("/user")
    public UserResponseDto getCurrentUser(Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow(
                () -> new RuntimeException("User is not authenticate")
        );
        return userResponseDtoMapper.mapToDto(user);
    }
}
