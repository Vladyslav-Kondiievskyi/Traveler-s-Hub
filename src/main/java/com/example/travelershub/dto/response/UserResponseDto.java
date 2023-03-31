package com.example.travelershub.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
    private List<Long> roleIds;
}
