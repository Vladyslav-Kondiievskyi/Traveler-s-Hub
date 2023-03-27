package com.example.travelershub.repository;

import com.example.travelershub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findAllByEmail(String email);

    User findUserByFirstNameAndLastName(String firstName,String lastName);

    User deleteUserByFirstNameAndLastName(String firstName,String lastName);
}
