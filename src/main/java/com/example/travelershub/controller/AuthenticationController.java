package com.example.travelershub.controller;

import com.example.travelershub.dto.request.userdto.UserRegistrationRequestDto;
import com.example.travelershub.dto.response.UserResponseDto;
import com.example.travelershub.model.User;
import com.example.travelershub.service.AuthenticationService;
import com.example.travelershub.service.mapper.RequestDtoMapper;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/booking","https://vanyachyzh.github.io/booking/"})
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper;
    private final RequestDtoMapper<UserRegistrationRequestDto, User> userRegistrationRequestDtoUserRequestDtoMapper;

    public AuthenticationController(AuthenticationService authenticationService, ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper, RequestDtoMapper<UserRegistrationRequestDto, User> userRegistrationRequestDtoUserRequestDtoMapper) {
        this.authenticationService = authenticationService;
        this.userResponseDtoMapper = userResponseDtoMapper;
        this.userRegistrationRequestDtoUserRequestDtoMapper = userRegistrationRequestDtoUserRequestDtoMapper;
    }

    @PostMapping("/register")
        public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        User user = authenticationService.register(userRegistrationRequestDtoUserRequestDtoMapper.mapToModel(requestDto));
        return userResponseDtoMapper.mapToDto(user);
    }
}
