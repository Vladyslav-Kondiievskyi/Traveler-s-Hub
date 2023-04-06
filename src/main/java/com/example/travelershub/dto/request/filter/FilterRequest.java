package com.example.travelershub.dto.request.filter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterRequest {
    private String sort;
    private Set<String> amenities;
    private List<String> apartmentType;
    private Integer capacity;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private String city;
    private String name;
    private Float rating;
    private Long reviews;
    private Byte stars;
    private LocalDate dateFrom;
    private LocalDate dateTo;// todo та сортування по ціні, рекомендаціям, зіркам та рейтингу
}
