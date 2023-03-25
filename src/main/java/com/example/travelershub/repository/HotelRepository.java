package com.example.travelershub.repository;

import com.example.travelershub.model.Hotel;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>,
        JpaSpecificationExecutor<Hotel> {
    List<Hotel> getAllByRatingBetween(BigDecimal ratingFrom, BigDecimal ratingTo);

    List<Hotel> findAllByRatingIsGreaterThan(BigDecimal rating);
}
