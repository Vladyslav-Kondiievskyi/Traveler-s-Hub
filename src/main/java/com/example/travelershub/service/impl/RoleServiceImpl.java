package com.example.travelershub.service.impl;

import com.example.travelershub.model.Role;
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
    public Role getByName(Role.RoleName user) {
        return repository.findByRoleName(user).get();
    }
}
