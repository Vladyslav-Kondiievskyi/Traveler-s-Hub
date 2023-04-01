package com.example.travelershub.dto.request.userdto;

import com.example.travelershub.lib.ValidEmail;
// todo need a password repeat ?

public class UserAuthentiticationRequestDto {
    private String password;
    @ValidEmail
    private String email;
}
