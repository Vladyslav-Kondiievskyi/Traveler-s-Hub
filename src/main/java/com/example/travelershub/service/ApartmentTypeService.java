package com.example.travelershub.service;

import com.example.travelershub.model.Apartment;
import com.example.travelershub.model.ApartmentType;
import com.example.travelershub.model.enumfolder.ApartmentKind;

import java.util.List;
import java.util.Set;

public interface ApartmentTypeService {
    List<ApartmentType> saveAll(Iterable<ApartmentType> entities);

    ApartmentType findByName(String name);
}
