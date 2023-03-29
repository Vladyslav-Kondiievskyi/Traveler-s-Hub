package com.example.travelershub.service.mapper;

import com.example.travelershub.dto.request.ReviewRequestDto;
import com.example.travelershub.dto.response.ReviewResponseDto;
import com.example.travelershub.model.Review;
import com.example.travelershub.service.HotelService;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements RequestDtoMapper<ReviewRequestDto, Review>,
        ResponseDtoMapper<ReviewResponseDto, Review> {
    private final HotelService hotelService;

    public ReviewMapper(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public Review mapToModel(ReviewRequestDto dto) {
        Review review = new Review();
        review.setRating(dto.getRating());
        review.setText(dto.getText());
        review.setAuthorName(dto.getAuthorName());
        review.setDate(dto.getDate());
        review.setHotel(hotelService.getById(dto.getHotelId()));
        return review;
    }

    @Override
    public ReviewResponseDto mapToDto(Review review) {
        ReviewResponseDto dto = new ReviewResponseDto();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setText(review.getText());
        dto.setAuthorName(review.getAuthorName());
        dto.setDate(review.getDate());
        dto.setHotelId(review.getHotel().getId());
        return dto;
    }
}
