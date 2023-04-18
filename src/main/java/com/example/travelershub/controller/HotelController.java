package com.example.travelershub.controller;

import com.example.travelershub.dto.request.FilterRequest;
import com.example.travelershub.dto.request.HotelRequestDto;
import com.example.travelershub.dto.response.HotelResponseDto;
import com.example.travelershub.model.Hotel;
import com.example.travelershub.service.HotelService;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
@CrossOrigin(origins = {"*"})
public class HotelController {
    private final HotelService hotelService;

    private final ResponseDtoMapper<HotelResponseDto, Hotel> hotelResponseDtoMapper;

    public HotelController(HotelService hotelService,
                           ResponseDtoMapper<HotelResponseDto, Hotel> hotelResponseDtoMapper) {
        this.hotelService = hotelService;
        this.hotelResponseDtoMapper = hotelResponseDtoMapper;
    }

    @GetMapping("/all")
    public List<HotelResponseDto> getAll() {
        return hotelService.getAll().stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<HotelResponseDto> findAllByRatingIsGreaterThan(@RequestParam Float minRating) {
        return hotelService.findAllByRatingIsGreaterThan(minRating)
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/rating")
    public List<HotelResponseDto> findAllByRatingBetween(@RequestParam Float from,
                                                         @RequestParam Float to) {
        return hotelService.getAllByRatingBetween(from, to)
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/stars")
    public List<HotelResponseDto> findAllByStarEqual(@RequestParam Byte star) {
        return hotelService.findAllByStarsIs(star)
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/name")
    public HotelResponseDto findByName(@RequestParam String hotelName) {
        return hotelResponseDtoMapper.mapToDto(hotelService.findByName(hotelName));
    }

    @GetMapping("/city")
    public List<HotelResponseDto> findAllByCity(@RequestParam String city) {
        return hotelService.findAllByCity(city)
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping ("/sort")
    public List<HotelRequestDto> sortByAllParameter(@RequestBody List<HotelRequestDto> dto,@RequestParam String sortBy) {
        return hotelService.sortHotels(dto,sortBy);
    }

    @GetMapping("/filters")
    public List<HotelResponseDto> findAllByFilter(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Float rating,
            @RequestParam(required = false) Byte stars,
            @RequestParam(required = false, defaultValue = "0") BigDecimal priceMin,
            @RequestParam(required = false, defaultValue = "1000000") BigDecimal priceMax,
            @RequestParam(required = false) Integer capacity,
            @RequestParam(required = false) Set<String> amenities,
            @RequestParam(required = false) List<String> apartmentType,
            @RequestParam(required = false,name = "dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false,name = "dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @RequestParam(required = false) String sort) {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setCity(city);
        filterRequest.setName(name);
        filterRequest.setRating(rating);
        filterRequest.setStars(stars);
        filterRequest.setDateFrom(dateFrom);
        filterRequest.setDateTo(dateTo);
        filterRequest.setSort(sort);
        filterRequest.setCapacity(capacity);
        filterRequest.setAmenities(amenities);
        filterRequest.setApartmentType(apartmentType);
        filterRequest.setPriceMin(priceMin);
        filterRequest.setPriceMax(priceMax);
        filterRequest.setSort(sort);
        return hotelService.filterHotels(filterRequest)
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/orders_desc")
    public List<HotelResponseDto> findAllOrderByReviewsCountDesc() {
        return hotelService.findAllOrderByReviewsCountDesc()
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("sort_reviews_by_hotel{hotelId}")
    public Map<Float, Integer> getReviewMapByHotelId(@PathVariable Long hotelId) {
        return hotelService.countReviewsByRating(hotelId);
    }
}
