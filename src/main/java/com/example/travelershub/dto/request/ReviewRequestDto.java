package com.example.travelershub.dto.request;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    private Float rating;
    private String text;
    private String authorName;
    private LocalDateTime date;
    private Long hotelId;
}
