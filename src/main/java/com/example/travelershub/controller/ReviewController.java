package com.example.travelershub.controller;

import com.example.travelershub.dto.response.ReviewResponseDto;
import com.example.travelershub.model.Review;
import com.example.travelershub.service.ReviewService;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ResponseDtoMapper<ReviewResponseDto, Review> responseDtoMapper;

    public ReviewController(ReviewService reviewService,
                            ResponseDtoMapper<ReviewResponseDto, Review> responseDtoMapper) {
        this.reviewService = reviewService;
        this.responseDtoMapper = responseDtoMapper;
    }

    @GetMapping("/{hotelId}")
    public List<ReviewResponseDto> getAllByHotel(@PathVariable Long hotelId) {
        return reviewService.getAllByHotel(hotelId)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
