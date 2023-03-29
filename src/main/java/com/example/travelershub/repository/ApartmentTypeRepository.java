package com.example.travelershub.repository;

import com.example.travelershub.model.ApartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentTypeRepository extends JpaRepository<ApartmentType, Long>,
        JpaSpecificationExecutor<ApartmentType> {
}
