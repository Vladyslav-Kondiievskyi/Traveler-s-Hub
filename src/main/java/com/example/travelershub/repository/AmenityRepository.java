package com.example.travelershub.repository;

import com.example.travelershub.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long>,
        JpaSpecificationExecutor<Amenity> {
    public Amenity getByName(Amenity.AmenityName name);
}
