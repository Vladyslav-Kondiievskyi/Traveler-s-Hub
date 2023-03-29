package com.example.travelershub.service;

import com.example.travelershub.model.ApartmentType;
import java.util.List;

public interface ApartmentTypeService {
    List<ApartmentType> saveAll(Iterable<ApartmentType> entities);

    ApartmentType findByName(String name);
}
