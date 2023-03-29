package com.example.travelershub.service.impl;

import com.example.travelershub.model.Hotel;
import com.example.travelershub.model.Review;
import com.example.travelershub.repository.HotelRepository;
import com.example.travelershub.repository.ReviewRepository;
import com.example.travelershub.service.ReviewService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final HotelRepository hotelRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, HotelRepository hotelRepository) {
        this.reviewRepository = reviewRepository;
        this.hotelRepository = hotelRepository;
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

    @Override
    public List<Review> getAllByHotel(Long hotelId) {
        return reviewRepository.getAllByHotel_Id(hotelId);
    }

    @Override
    public void addReviewToHotel(Long hotelId, Review review) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id " + hotelId));
        List<Review> reviews = hotel.getReviews();
        reviews.add(review);
        review.setHotel(hotel);
        hotel.setReviews(reviews);
        reviewRepository.save(review);
        hotelRepository.save(hotel);
    }
}
