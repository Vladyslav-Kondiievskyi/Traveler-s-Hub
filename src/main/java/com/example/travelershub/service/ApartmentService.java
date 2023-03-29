package com.example.travelershub.service;

import com.example.travelershub.model.Apartment;
import java.math.BigDecimal;
import java.util.List;

public interface ApartmentService {

    Apartment save(Apartment room);

    Apartment getById(Long id);

    Apartment update(Apartment room);

    void deleteById(Long id);

    List<Apartment> findAllByPriceBetween(BigDecimal from, BigDecimal to);
}
