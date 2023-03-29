package com.example.travelershub.service.mapper;

import com.example.travelershub.dto.response.HotelResponseDto;
import com.example.travelershub.model.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper implements ResponseDtoMapper<HotelResponseDto, Hotel> {

    @Override
    public HotelResponseDto mapToDto(Hotel hotel) {
        HotelResponseDto dto = new HotelResponseDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setPicturesUrl(hotel.getPicturesUrl());
        dto.setAddress(hotel.getAddress());
        dto.setCity(hotel.getCity());
        dto.setTelephone(hotel.getTelephone());
        dto.setDescription(hotel.getDescription());
        dto.setRating(hotel.getRating());
        dto.setStars(hotel.getStars());
        return dto;
    }
}
