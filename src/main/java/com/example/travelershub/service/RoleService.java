package com.example.travelershub.service;

import com.example.travelershub.model.Role;

public interface RoleService {
    Role getByName(Role.RoleName user);
}
