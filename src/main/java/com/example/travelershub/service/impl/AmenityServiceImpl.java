package com.example.travelershub.service.impl;

import com.example.travelershub.model.Amenity;
import com.example.travelershub.repository.AmenityRepository;
import com.example.travelershub.service.AmenityService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AmenityServiceImpl implements AmenityService {
    private final AmenityRepository amenityRepository;

    public AmenityServiceImpl(AmenityRepository amenityRepository) {
        this.amenityRepository = amenityRepository;
    }

    @Override
    public Amenity add(Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    @Override
    public Amenity findByName(Amenity.AmenityName name) {
        return amenityRepository.getByName(name);
    }

    @Override
    public List<Amenity> saveAll(Iterable<Amenity> entities) {
        return amenityRepository.saveAll(entities);
    }
}
