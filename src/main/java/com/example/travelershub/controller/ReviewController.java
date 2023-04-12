package com.example.travelershub.controller;

import com.example.travelershub.dto.request.ReviewRequestDto;
import com.example.travelershub.dto.response.ReviewResponseDto;
import com.example.travelershub.model.Review;
import com.example.travelershub.service.HotelService;
import com.example.travelershub.service.ReviewService;
import com.example.travelershub.service.mapper.RequestDtoMapper;
import com.example.travelershub.service.mapper.ResponseDtoMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = {"*"})
public class ReviewController {
    private final ReviewService reviewService;
    private final ResponseDtoMapper<ReviewResponseDto, Review> responseDtoMapper;
    private final RequestDtoMapper<ReviewRequestDto, Review> requestDtoMapper;
    private final HotelService hotelService;

    public ReviewController(ReviewService reviewService,
                            ResponseDtoMapper<ReviewResponseDto, Review> responseDtoMapper, RequestDtoMapper<ReviewRequestDto, Review> requestDtoMapper, HotelService hotelService) {
        this.reviewService = reviewService;
        this.responseDtoMapper = responseDtoMapper;
        this.requestDtoMapper = requestDtoMapper;
        this.hotelService = hotelService;
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

    @GetMapping("/inject/{hotelId}")
    public void injectReviewsToHotel(@PathVariable Long hotelId, @RequestParam int count) {
        for (int i = 0; i < count; i++) {
            Float rating = generateRating();
            String text = generateText();
            String authorName = generateAuthorName();
            LocalDateTime date = generateDate();
            Review review = new Review();
            review.setHotel(hotelService.getById(hotelId));
            review.setText(text);
            review.setRating(rating);
            review.setAuthorName(authorName);
            review.setDate(date);
            reviewService.save(review);
        }
    }

    private static Float generateRating() {
        Random random = new Random();
        return (float) (random.nextInt(6));
    }

    private static String generateText() {
        String[] words = {"lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "sed", "do",
                "eiusmod", "tempor", "incididunt", "ut", "labore", "et", "dolore", "magna", "aliqua", "Ut", "enim",
                "ad", "minim", "veniam", "quis", "nostrud", "exercitation", "ullamco", "laboris", "nisi", "ut",
                "aliquip", "ex", "ea", "commodo", "consequat", "Duis", "aute", "irure", "dolor", "in", "reprehenderit",
                "in", "voluptate", "velit", "esse", "cillum", "dolore", "eu", "fugiat", "nulla", "pariatur", "Excepteur",
                "sint", "occaecat", "cupidatat", "non", "proident", "sunt", "in", "culpa", "qui", "officia", "deserunt",
                "mollit", "anim", "id", "est", "laborum"};
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int wordCount = random.nextInt(30) + 10;
        for (int i = 0; i < wordCount; i++) {
            int randomIndex = random.nextInt(words.length);
            sb.append(words[randomIndex]).append(" ");
        }
        return sb.toString().trim();
    }

    private static String generateAuthorName() {
        String[] names = {"Alice", "Bob", "Charlie", "Dave", "Eve", "Frank", "Grace", "Heidi", "Ivan", "Judy", "Kevin",
                "Laura", "Mallory", "Ned", "Olivia", "Peggy", "Quentin", "Ralph", "Steve", "Trudy", "Victor", "Wendy",
                "Xavier", "Yvonne", "Zach"};
        Random random = new Random();
        int randomIndex = random.nextInt(names.length);
        return names[randomIndex];
    }

    private static LocalDateTime generateDate() {
        Random random = new Random();
        int year = random.nextInt(10) + 2010;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        return LocalDateTime.of(year, month, day, hour, minute, 0);
    }
}
