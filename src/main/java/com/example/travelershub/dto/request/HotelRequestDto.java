package com.example.travelershub.dto.request;

import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelRequestDto {
    private Long id;
    private List<String> picturesUrl;
    private Set<String> amenities;
    private String address;
    private String city;
    private String telephone;
    private String name;
    private String description;
    private Float rating;
    private Byte stars;
    private String price;
    private Long allReviews;
}
