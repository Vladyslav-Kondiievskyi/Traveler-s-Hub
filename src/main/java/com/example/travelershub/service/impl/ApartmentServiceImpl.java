package com.example.travelershub.service.impl;

import com.example.travelershub.model.Apartment;
import com.example.travelershub.model.ApartmentType;
import com.example.travelershub.repository.ApartmentRepository;
import com.example.travelershub.service.ApartmentService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;

    public ApartmentServiceImpl(ApartmentRepository roomRepository) {
        this.apartmentRepository = roomRepository;
    }

    @Override
    public Apartment save(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @Override
    public List<Apartment> getAll() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment getById(Long id) {
        return apartmentRepository.findById(id).get();
    }

    @Override
    public List<Apartment> getAllByAmenities(Set<String> amenities, long amenitySize) {
        return apartmentRepository.findByAmenitiesIn(amenities, amenitySize);
    }

    @Override
    public List<Apartment> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return apartmentRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Apartment> findAllByCapacityBetween(Integer from, Integer to) {
        return apartmentRepository.findAllByCapacityBetween(from, to);
    }

    @Override
    public List<Apartment> findAllByApartmentType(ApartmentType apartmentType) {
        return apartmentRepository.findAllByApartmentType(apartmentType);
    }

    @Override
    public List<Apartment> findAllByHotelId(Long hotelId) {
        return apartmentRepository.findAllByHotelId(hotelId);
    }

    @Override
    public List<Apartment> findAllByOrderByPriceDesc() {
        return apartmentRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public List<Apartment> findAllByOrderByPriceAsc() {
        return apartmentRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Apartment> findAllByPriceBetweenOrderByPriceAsc(BigDecimal from, BigDecimal to) {
        return apartmentRepository.findAllByPriceBetweenOrderByPriceAsc(from, to);
    }

    @Override
    public List<Apartment> findAllByPriceBetweenOrderByPriceDesc(BigDecimal from, BigDecimal to) {
        return apartmentRepository.findAllByPriceBetweenOrderByPriceDesc(from, to);
    }
}
