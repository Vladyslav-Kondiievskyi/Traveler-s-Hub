package com.example.travelershub.service.mapper;

import com.example.travelershub.dto.response.HotelResponseDto;
import com.example.travelershub.model.Hotel;
import com.example.travelershub.service.HotelService;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper implements ResponseDtoMapper<HotelResponseDto, Hotel> {
    private final HotelService hotelService;

    public HotelMapper(HotelService hotelService) {
        this.hotelService = hotelService;
    }

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
        dto.setAllReviews((long) hotel.getReviews().size());
        dto.setPrice(String.valueOf(hotel.getRooms().get(0).getPrice()));
        dto.setAmenities(hotelService.getMainAmenities(hotel.getRooms()));
        return dto;
    }
}
