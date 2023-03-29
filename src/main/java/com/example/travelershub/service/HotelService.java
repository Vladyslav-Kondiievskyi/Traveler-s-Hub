package com.example.travelershub.service;

import com.example.travelershub.model.Hotel;
import com.example.travelershub.model.Review;
import java.util.List;

public interface HotelService {
    Hotel save(Hotel hotel);

    Hotel getById(Long id);

    Hotel addReview(Long hotelId, Review review);

    List<Hotel> getAll();

    void deleteById(Long id);

    List<Hotel> getAllByRatingBetween(Float ratingFrom, Float ratingTo);

    List<Hotel> findAllByRatingIsGreaterThan(Float rating);

    void addReviewToHotel(Long hotelId, Review review);
}
