package com.example.travelershub.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelResponseDto {
    private Long id;
    private List<String> picturesUrl;
    private String address;
    private String city;
    private String telephone;
    private String name;
    private String description;
    private Float rating;
    private Byte stars;
}
