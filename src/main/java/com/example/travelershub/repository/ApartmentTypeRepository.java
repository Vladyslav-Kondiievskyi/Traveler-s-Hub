package com.example.travelershub.repository;

import com.example.travelershub.model.ApartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentTypeRepository extends JpaRepository<ApartmentType, Long>,
        JpaSpecificationExecutor<ApartmentType> {
    @Query("from ApartmentType a where a.apartmentKind = CAST(:name AS com.example.travelershub.model.enumfolder.ApartmentKind)")
    ApartmentType findByName(@Param("name") String name);
}
