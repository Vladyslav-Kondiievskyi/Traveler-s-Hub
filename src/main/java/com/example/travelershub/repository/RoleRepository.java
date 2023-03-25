package com.example.travelershub.repository;

import java.util.Optional;
import com.example.travelershub.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

// todo is correct use Enum in method parameter?
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>,
        JpaSpecificationExecutor<Role> {
    Optional<Role> findByRoleName(Role.RoleName roleName);
}
