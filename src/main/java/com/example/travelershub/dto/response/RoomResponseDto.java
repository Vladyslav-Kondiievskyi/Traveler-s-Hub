package com.example.travelershub.dto.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomResponseDto {
    private Long id;
    private int number;
    private BigDecimal price;
    private int capacity;
    private List<String> picturesUrl;
}
