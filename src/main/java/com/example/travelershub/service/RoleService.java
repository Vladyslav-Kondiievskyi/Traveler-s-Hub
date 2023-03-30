package com.example.travelershub.service;

import com.example.travelershub.model.Role;
import com.example.travelershub.model.enumfolder.RoleName;

public interface RoleService {
    Role getByName(RoleName roleName);

    Role save(Role role);
}
