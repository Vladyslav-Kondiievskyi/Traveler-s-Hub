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
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public List<Hotel> getAllByRatingBetween(Float ratingFrom, Float ratingTo) {
        return hotelRepository.getAllByRatingBetween(ratingFrom, ratingTo);
    }

    @Override
    public List<Hotel> findAllByRatingIsGreaterThan(Float rating) {
        return hotelRepository.findAllByRatingIsGreaterThanEqual(rating);
    }

    @Override
    public List<Hotel> findAllByStarsIs(Byte stars) {
        return hotelRepository.findAllByStarsIs(stars);
    }

    @Override
    public Hotel findByName(String hotelName) {
        return hotelRepository.findByName(hotelName);
    }

    @Override
    public List<Hotel> findAllByCity(String city) {
        return hotelRepository.findAllByCity(city);
    }

    @Override
    public List<Hotel> findAllOrderByReviewsCountDesc() {
        return hotelRepository.findAllOrderByReviewsCountDesc();
    }
}
