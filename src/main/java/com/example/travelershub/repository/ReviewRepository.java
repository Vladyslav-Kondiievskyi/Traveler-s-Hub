package com.example.travelershub.repository;

import com.example.travelershub.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getAllByHotel_Id(Long hotelId);

    @Modifying
    @Query("update Review r set r.text =?2, r.rating=?3 where r.id=?1")
    Review setInfoById(Long id, String text, Float rating);
}
