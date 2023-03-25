package com.example.travelershub.service;

import com.example.travelershub.model.Amenity;
import java.util.List;

public interface AmenityService {
    Amenity add(Amenity amenity);

    Amenity findByName(Amenity.AmenityName name);

    List<Amenity> saveAll(Iterable<Amenity> entities);
}
