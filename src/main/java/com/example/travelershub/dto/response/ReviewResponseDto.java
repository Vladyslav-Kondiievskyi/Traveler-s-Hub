package com.example.travelershub.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDto {
    private Long id;
    private Integer rating;
    private String text;
    private String authorName;
    private LocalDateTime date;
}
