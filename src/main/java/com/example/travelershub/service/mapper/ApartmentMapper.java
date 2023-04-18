package com.example.travelershub.service.mapper;

import com.example.travelershub.dto.response.ApartmentResponseDto;
import com.example.travelershub.model.Apartment;
import org.springframework.stereotype.Component;

@Component
public class ApartmentMapper implements ResponseDtoMapper<ApartmentResponseDto, Apartment> {

    @Override
    public ApartmentResponseDto mapToDto(Apartment apartment) {
        ApartmentResponseDto dto = new ApartmentResponseDto();
        dto.setId(apartment.getId());
        dto.setNumber(apartment.getNumber());
        dto.setName(apartment.getName());
        dto.setPrice(apartment.getPrice());
        dto.setCapacity(apartment.getCapacity());
        dto.setApartmentTypeId(apartment.getApartmentType().getId());
        dto.setAmenities(apartment.getAmenities());
        dto.setHotelName(apartment.getHotel().getName());
        return dto;
    }
}
