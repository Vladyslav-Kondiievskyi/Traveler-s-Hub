package com.example.travelershub.service.impl;

import com.example.travelershub.model.Apartment;
import com.example.travelershub.model.ApartmentType;
import com.example.travelershub.repository.ApartmentTypeRepository;
import com.example.travelershub.service.ApartmentTypeService;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class ApartmentTypeServiceImpl implements ApartmentTypeService {
    private final ApartmentTypeRepository apartmentTypeRepository;

    public ApartmentTypeServiceImpl(ApartmentTypeRepository apartmentTypeRepository) {
        this.apartmentTypeRepository = apartmentTypeRepository;
    }

    @Override
    public List<ApartmentType> saveAll(Iterable<ApartmentType> entities) {
        return apartmentTypeRepository.saveAll(entities);
    }

    @Override
    public ApartmentType findByName(String name) {
        return apartmentTypeRepository.findByName(name);
    }
}
