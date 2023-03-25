package com.example.travelershub.service;

import com.example.travelershub.model.Review;

public interface ReviewService {
    Review save(Review review);

    Review getById(Long id);

    Review update(Review review);

    void deleteById(Long id);
}
