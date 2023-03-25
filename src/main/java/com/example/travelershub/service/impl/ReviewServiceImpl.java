package com.example.travelershub.service.impl;

import com.example.travelershub.model.Review;
import com.example.travelershub.repository.ReviewRepository;
import com.example.travelershub.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getById(Long id) {
        return reviewRepository.findById(id).get();
    }

    @Override
    public Review update(Review review) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
