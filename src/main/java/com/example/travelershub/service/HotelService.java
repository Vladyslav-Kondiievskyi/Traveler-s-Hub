package com.example.travelershub.service;

import com.example.travelershub.model.Amenity;
import com.example.travelershub.model.Hotel;
import com.example.travelershub.model.Review;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface HotelService {
    Hotel save(Hotel hotel);

    Hotel getById(Long id);

    Hotel addReview(Long hotelId, Review review);

    List<Hotel> getAll();

    void deleteById(Long id);

    List<Hotel> getAllByRatingBetween(BigDecimal ratingFrom, BigDecimal ratingTo);

    List<Hotel> findAllByRatingIsGreaterThan(BigDecimal rating);

    List<Hotel> findHotelByAmenities(@Param("amenities") List<Amenity.AmenityName> amenities);

    void addReviewToHotel(Long hotelId, Review review);
}
