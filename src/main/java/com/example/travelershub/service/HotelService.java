package com.example.travelershub.service;

import com.example.travelershub.model.Hotel;
import java.math.BigDecimal;
import java.util.List;

public interface HotelService {
    Hotel save(Hotel hotel);

    Hotel getById(Long id);

    Hotel update(Hotel hotel);

    void deleteById(Long id);

    List<Hotel> getAllByRatingBetween(BigDecimal ratingFrom, BigDecimal ratingTo);

    List<Hotel> findAllByRatingIsGreaterThan(BigDecimal rating);
}
