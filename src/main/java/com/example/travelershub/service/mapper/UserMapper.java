package com.example.travelershub.service.mapper;

import com.example.travelershub.dto.request.userdto.UserRegistrationRequestDto;
import com.example.travelershub.dto.response.UserResponseDto;
import com.example.travelershub.model.Role;
import com.example.travelershub.model.User;
import com.example.travelershub.model.enumfolder.RoleName;
import com.example.travelershub.service.RoleService;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements
        ResponseDtoMapper<UserResponseDto, User>, RequestDtoMapper<UserRegistrationRequestDto, User> {
    private final RoleService roleService;

    public UserMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setEmail(user.getEmail());
        responseDto.setId(user.getId());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setTelephone(user.getTelephone());
        responseDto.setRoleIds(user.getRoles().stream()
                .map(Role::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }

    @Override
    public User mapToModel(UserRegistrationRequestDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setTelephone(dto.getTelephone());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRoles(Set.of(roleService.getByName(RoleName.USER)));
        return user;
    }
}
