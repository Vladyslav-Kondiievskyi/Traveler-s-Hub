package com.example.travelershub.repository;

import com.example.travelershub.model.Apartment;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long>,
        JpaSpecificationExecutor<Apartment> {
    List<Apartment> findAllByPriceBetween(BigDecimal from, BigDecimal to);
}
