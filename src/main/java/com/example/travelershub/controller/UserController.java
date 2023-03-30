package com.example.travelershub.controller;

import com.example.travelershub.dto.response.UserResponseDto;
import com.example.travelershub.model.Role;
import com.example.travelershub.model.User;
import com.example.travelershub.model.enumfolder.RoleName;
import com.example.travelershub.service.RoleService;
import com.example.travelershub.service.UserService;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import java.util.Random;
import java.util.Set;
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
    private final RoleService roleService;
    private final ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper;

    public UserController(UserService userService,
                          RoleService roleService, ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper) {
        this.userService = userService;
        this.roleService = roleService;
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

    @GetMapping("/delete/{id}")
    public UserResponseDto deleteById(@PathVariable Long id) {
        User user = userService.get(id);
        return userResponseDtoMapper.mapToDto(user);
    }

    @GetMapping("/inject")
    public String inject(@RequestParam(defaultValue = "5") Integer count) {
        String[] firstNames = {"John", "Elly", "Bob", "Van", "Ted",};
        String[] lastNames = {"Doe", "Banji", "Rosse", "Chin", "Marshall"};
        String[] passwords = {"johny3214", "elly3301", "bob4e122", "teddy2005", "jinny2002"};
        String[] emails = {"travel@gmail.com", "john@gmail.com", "bob@gmail.com", "dony@gmail.com", "elly@gmail.com"};
        String[] telephones = {"380-978-920-040", "380-654-934-560", "380-638-220-631", "380-678-988-143", "380-955-321-908"};
        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ADMIN);
        roleService.save(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(RoleName.USER);
        roleService.save(userRole);
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            User user = new User();
            int randomElement = random.nextInt(5);
            user.setTelephone(telephones[randomElement]);
            user.setFirstName(firstNames[randomElement]);
            user.setLastName(lastNames[randomElement]);
            user.setEmail(emails[randomElement]);
            user.setPassword(passwords[randomElement]);
            user.setRoles(Set.of(roleService.getByName(RoleName.USER)));
            userService.save(user);
        }
        return "Create " + count + " users";
    }
}
