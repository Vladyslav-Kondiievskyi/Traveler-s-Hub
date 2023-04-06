package com.example.travelershub.service;

import com.example.travelershub.dto.request.filter.FilterRequest;
import com.example.travelershub.model.Hotel;
import java.util.List;

public interface HotelService {
    Hotel save(Hotel hotel);

    Hotel getById(Long id);

    List<Hotel> getAll();

    List<Hotel> getAllByRatingBetween(Float ratingFrom, Float ratingTo);

    List<Hotel> findAllByRatingIsGreaterThan(Float rating);

    List<Hotel> findAllByStarsIs(Byte stars);

    Hotel findByName(String hotelName);

    List<Hotel> findAllByCity(String city);

    List<Hotel> findAllOrderByReviewsCountDesc();

    List<Hotel> filterHotels(FilterRequest filters);
}
