package com.example.travelershub.service.mapper;

import com.example.travelershub.dto.response.UserResponseDto;
import com.example.travelershub.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements
        ResponseDtoMapper<UserResponseDto, User> {
    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setEmail(user.getEmail());
        responseDto.setId(user.getId());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setTelephone(user.getTelephone());
        return responseDto;
    }
}
