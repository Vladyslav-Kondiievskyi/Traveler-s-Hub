package com.example.travelershub.dto.request;

import com.example.travelershub.lib.ValidEmail;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDto {
    @Size(min = 1)
    private String firstName;
    @Size(min = 1)
    private String lastName;
    @ValidEmail
    private String email;
    @Size(min = 6, max = 13)
    private String telephone;
    private String password;
}
