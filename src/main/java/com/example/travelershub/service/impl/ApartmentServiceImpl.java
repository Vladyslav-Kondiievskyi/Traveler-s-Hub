package com.example.travelershub.service.impl;

import com.example.travelershub.model.Apartment;
import com.example.travelershub.repository.ApartmentRepository;
import com.example.travelershub.service.ApartmentService;
import java.math.BigDecimal;
import java.util.List;
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
    public Apartment getById(Long id) {
        return apartmentRepository.findById(id).get();
    }

    @Override
    public Apartment update(Apartment room) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        apartmentRepository.deleteById(id);
    }

    @Override
    public List<Apartment> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return apartmentRepository.findAllByPriceBetween(from, to);
    }
}
