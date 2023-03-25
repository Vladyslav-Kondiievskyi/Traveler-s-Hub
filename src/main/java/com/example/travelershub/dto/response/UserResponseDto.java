package com.example.travelershub.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//todo this dto for front side and is it necessary to search by id?
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
}
