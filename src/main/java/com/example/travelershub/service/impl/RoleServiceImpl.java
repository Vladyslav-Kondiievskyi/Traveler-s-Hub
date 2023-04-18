package com.example.travelershub.service.impl;

import com.example.travelershub.model.Role;
import com.example.travelershub.model.enumfolder.RoleName;
import com.example.travelershub.repository.RoleRepository;
import com.example.travelershub.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role getByName(RoleName roleName) {
        return repository.findByRoleName(roleName).orElseThrow(
                () -> new RuntimeException("Role by name " + roleName + " not found")
        );
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }
}
