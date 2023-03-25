package com.example.travelershub.dto.request;

import lombok.Getter;
import lombok.Setter;

// todo in future need requestDto for authorization only with email and password
@Getter
@Setter
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;
}
