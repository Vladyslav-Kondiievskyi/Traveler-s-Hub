package com.example.travelershub.service;

import com.example.travelershub.model.Review;
import java.util.List;

public interface ReviewService {
    Review save(Review review);

    Review getById(Long id);

    Review update(Review review);

    void deleteById(Long id);

    List<Review> getAllByHotel(Long hotelId);

    void addReviewToHotel(Long hotelId, Review review);
}
