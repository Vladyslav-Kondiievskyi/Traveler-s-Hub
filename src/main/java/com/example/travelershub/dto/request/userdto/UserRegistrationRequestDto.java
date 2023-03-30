package com.example.travelershub.dto.request.userdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;
}
