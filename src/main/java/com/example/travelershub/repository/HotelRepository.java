package com.example.travelershub.repository;

import com.example.travelershub.model.Hotel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>,
        JpaSpecificationExecutor<Hotel>, HotelSpecification {
    List<Hotel> getAllByRatingBetween(Float ratingFrom, Float ratingTo);

    List<Hotel> findAllByRatingIsGreaterThanEqual(Float rating);

    List<Hotel> findAllByStarsIs(Byte stars);

    Hotel findByName(String hotelName);

    List<Hotel> findAllByCity(String city);

    @Query("SELECT h FROM Hotel h JOIN h.reviews r GROUP BY h.id ORDER BY COUNT(r) DESC")
    List<Hotel> findAllOrderByReviewsCountDesc();

    @Query("SELECT COUNT(r) FROM Hotel h JOIN h.reviews r WHERE h.id = :hotelId AND FLOOR(r.rating) = :rating")
    Integer countReviewsByRating(@Param("hotelId") Long hotelId, @Param("rating") Integer rating);
}
