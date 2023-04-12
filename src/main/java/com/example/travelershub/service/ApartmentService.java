package com.example.travelershub.service;

import com.example.travelershub.model.Apartment;
import com.example.travelershub.model.ApartmentType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ApartmentService {

    Apartment save(Apartment room);

    List<Apartment> getAll();

    Apartment getById(Long id);

    List<Apartment> getAllByAmenities(Set<String> amenities, long amenitySize);

    List<Apartment> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Apartment> findAllByCapacityBetween(Integer from, Integer to);

    List<Apartment> findAllByApartmentType(ApartmentType apartmentType);

    List<Apartment> findAllByHotelId(Long hotelId);

    List<Apartment> findAllByOrderByPriceDesc();

    List<Apartment> findAllByOrderByPriceAsc();

    List<Apartment> findAllByPriceBetweenOrderByPriceAsc(BigDecimal from, BigDecimal to);

    List<Apartment> findAllByPriceBetweenOrderByPriceDesc(BigDecimal from, BigDecimal to);

    List<Apartment> findAvailableApartments(Long hotelId, Integer capacity, LocalDate dateFrom, LocalDate dateTo);
}
