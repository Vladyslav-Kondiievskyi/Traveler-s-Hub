package com.example.travelershub.repository;

import com.example.travelershub.model.Amenity;
import com.example.travelershub.model.Hotel;
import com.example.travelershub.model.Review;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>,
        JpaSpecificationExecutor<Hotel> {
    List<Hotel> getAllByRatingBetween(BigDecimal ratingFrom, BigDecimal ratingTo);

    List<Hotel> findAllByRatingIsGreaterThanEqual(BigDecimal rating);

    @Query("SELECT h FROM Hotel h JOIN h.amenities a WHERE a.name IN :amenities")
    List<Hotel> findHotelByAmenities(@Param("amenities") List<Amenity.AmenityName> amenities);

    @Modifying
    @Query("UPDATE Hotel h SET h.reviews = :reviews WHERE h.id = :hotelId")
    void updateHotelReviews(@Param("hotelId") Long hotelId, @Param("reviews") List<Review> reviews);

    @Query("from Review r join fetch Hotel h join fetch h.reviews where h.id = :hotelId")
    List<Review> getAllReviewsByHotel(@Param("hotelId") Long hotelId);
}
