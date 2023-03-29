package com.example.travelershub.service.impl;

import com.example.travelershub.model.Hotel;
import com.example.travelershub.model.Review;
import com.example.travelershub.repository.HotelRepository;
import com.example.travelershub.repository.ReviewRepository;
import com.example.travelershub.service.HotelService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final ReviewRepository reviewRepository;

    public HotelServiceImpl(HotelRepository hotelRepository, ReviewRepository reviewRepository) {
        this.hotelRepository = hotelRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getById(Long id) {
        return hotelRepository.findById(id).get();
    }

    @Override
    public Hotel addReview(Long hotelId, Review review) {
        Hotel hotelFromDB = hotelRepository.findById(hotelId).get();
        List<Review> hotelFromDbReviews = hotelFromDB.getReviews();
        hotelFromDbReviews.add(review);
        hotelFromDB.setReviews(hotelFromDbReviews);
        return hotelRepository.save(hotelFromDB);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public List<Hotel> getAllByRatingBetween(Float ratingFrom, Float ratingTo) {
        return hotelRepository.getAllByRatingBetween(ratingFrom, ratingTo);
    }

    @Override
    public List<Hotel> findAllByRatingIsGreaterThan(Float rating) {
        return hotelRepository.findAllByRatingIsGreaterThanEqual(rating);
    }

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
