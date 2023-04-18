package com.example.travelershub.dto.response;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartmentResponseDto {
    private Long id;
    private String name;
    private int number;
    private BigDecimal price;
    private int capacity;
    private Long apartmentTypeId;
    private Set<String> amenities;
    private String hotelName;
}
