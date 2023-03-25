package com.example.travelershub.service.mapper;

import com.example.travelershub.dto.response.HotelResponseDto;
import com.example.travelershub.model.Amenity;
import com.example.travelershub.model.Hotel;
import com.example.travelershub.model.Review;
import com.example.travelershub.model.Room;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper implements ResponseDtoMapper<HotelResponseDto, Hotel> {

    @Override
    public HotelResponseDto mapToDto(Hotel hotel) {
        HotelResponseDto dto = new HotelResponseDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setRoomsId(hotel.getRooms().stream()
                .map(Room::getId)
                .collect(Collectors.toList()));
        dto.setReviewsId(hotel.getReviews().stream()
                .map(Review::getId)
                .collect(Collectors.toList()));
        dto.setAmenitiesId(hotel.getAmenities().stream()
                .map(Amenity::getId)
                .collect(Collectors.toList()));
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
