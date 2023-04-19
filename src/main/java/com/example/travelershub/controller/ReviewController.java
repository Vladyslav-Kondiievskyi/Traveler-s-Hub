package com.example.travelershub.controller;

import com.example.travelershub.dto.request.ReviewRequestDto;
import com.example.travelershub.dto.response.ReviewResponseDto;
import com.example.travelershub.model.Review;
import com.example.travelershub.service.ReviewService;
import com.example.travelershub.service.mapper.RequestDtoMapper;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = {"*"})
public class ReviewController {
    private final ReviewService reviewService;
    private final ResponseDtoMapper<ReviewResponseDto, Review> responseDtoMapper;
    private final RequestDtoMapper<ReviewRequestDto, Review> requestDtoMapper;

    public ReviewController(ReviewService reviewService,
                            ResponseDtoMapper<ReviewResponseDto, Review> responseDtoMapper,
                            RequestDtoMapper<ReviewRequestDto, Review> requestDtoMapper) {
        this.reviewService = reviewService;
        this.responseDtoMapper = responseDtoMapper;
        this.requestDtoMapper = requestDtoMapper;
    }

    @GetMapping("/{hotelId}")
    public List<ReviewResponseDto> getAllByHotel(@PathVariable Long hotelId) {
        return reviewService.getAllByHotel(hotelId)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/add/{hotelId}")
    public ReviewResponseDto addReviewToHotel(@PathVariable Long hotelId,
                                              @RequestBody @Valid ReviewRequestDto requestDto) {
        requestDto.setHotelId(hotelId);
        Review review = requestDtoMapper.mapToModel(requestDto);
        reviewService.addReviewToHotel(hotelId, review);
        return responseDtoMapper.mapToDto(review);
    }
}
