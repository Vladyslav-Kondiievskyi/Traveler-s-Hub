package com.example.travelershub.dto.request;

import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// todo maybe need the name of the hotel between instead of hotelId?
@Getter
@Setter
public class ReviewRequestDto {
    @Min(0)
    @Max(5)
    private Float rating;
    @Size(max = 600)
    private String text;
    private String authorName;
    private LocalDateTime date;
    private Long hotelId;
}
