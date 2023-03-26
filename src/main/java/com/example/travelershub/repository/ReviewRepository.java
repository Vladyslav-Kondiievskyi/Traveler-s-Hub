package com.example.travelershub.repository;

import com.example.travelershub.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Hotel h JOIN h.reviews r WHERE h.id = :hotelId")
    List<Review> getAllByHotel(@Param("hotelId") Long hotelId);
}
