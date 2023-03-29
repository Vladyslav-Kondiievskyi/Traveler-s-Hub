package com.example.travelershub.repository;

import com.example.travelershub.model.Role;
import com.example.travelershub.model.enumfolder.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

// todo is correct use Enum in method parameter?
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>,
        JpaSpecificationExecutor<Role> {
    Optional<Role> findByRoleName(RoleName roleName);
}
