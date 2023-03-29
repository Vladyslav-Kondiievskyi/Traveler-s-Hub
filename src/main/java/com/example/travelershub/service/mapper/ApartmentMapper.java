package com.example.travelershub.service.mapper;

import com.example.travelershub.dto.response.RoomResponseDto;
import com.example.travelershub.model.Apartment;
import org.springframework.stereotype.Component;

@Component
public class ApartmentMapper implements ResponseDtoMapper<RoomResponseDto, Apartment> {

    @Override
    public RoomResponseDto mapToDto(Apartment room) {
        RoomResponseDto dto = new RoomResponseDto();
        dto.setId(room.getId());
        dto.setNumber(room.getNumber());
        dto.setPrice(room.getPrice());
        dto.setCapacity(room.getCapacity());
        dto.setPicturesUrl(room.getPicturesUrl());
        return dto;
    }
}
