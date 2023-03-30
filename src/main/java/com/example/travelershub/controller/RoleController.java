package com.example.travelershub.controller;

import com.example.travelershub.model.Role;
import com.example.travelershub.model.enumfolder.RoleName;
import com.example.travelershub.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// todo need after create-drop and need validate DISTINCT
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/inject")
    public String inject(@RequestParam(defaultValue = "5") Integer count) {
        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ADMIN);
        roleService.save(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(RoleName.USER);
        roleService.save(userRole);
        return "create 2 Roles";
    }
}
