package com.example.travelershub.service.impl;

import com.example.travelershub.model.Hotel;
import com.example.travelershub.repository.HotelRepository;
import com.example.travelershub.service.HotelService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
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
    public Hotel update(Hotel hotel) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public List<Hotel> getAllByRatingBetween(BigDecimal ratingFrom, BigDecimal ratingTo) {
        return hotelRepository.getAllByRatingBetween(ratingFrom, ratingTo);
    }

    @Override
    public List<Hotel> findAllByRatingIsGreaterThan(BigDecimal rating) {
        return hotelRepository.findAllByRatingIsGreaterThan(rating);
    }
}
