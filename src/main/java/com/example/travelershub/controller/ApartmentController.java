package com.example.travelershub.controller;

import com.example.travelershub.dto.response.ApartmentResponseDto;
import com.example.travelershub.model.Apartment;
import com.example.travelershub.model.ApartmentType;
import com.example.travelershub.service.ApartmentService;
import com.example.travelershub.service.ApartmentTypeService;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apartments")
@CrossOrigin(origins = {"http://localhost:3000/booking","https://vanyachyzh.github.io/booking/"})
public class ApartmentController {
    private final ApartmentService apartmentService;
    private final ResponseDtoMapper<ApartmentResponseDto, Apartment> apartmentResponseDtoMapper;
    private final ApartmentTypeService apartmentTypeService;

    public ApartmentController(ApartmentService apartmentService, ResponseDtoMapper<ApartmentResponseDto, Apartment>
            apartmentResponseDtoMapper, ApartmentTypeService apartmentTypeService) {
        this.apartmentService = apartmentService;
        this.apartmentResponseDtoMapper = apartmentResponseDtoMapper;
        this.apartmentTypeService = apartmentTypeService;
    }

    @GetMapping
    public List<ApartmentResponseDto> getApartmentsByAmenities(@RequestParam(required = false) Set<String> amenities) {
        if (amenities != null && !amenities.isEmpty()) {
            return apartmentService.getAllByAmenities(amenities, amenities.size())
                    .stream()
                    .map(apartmentResponseDtoMapper::mapToDto)
                    .collect(Collectors.toList());
        } else {
            return apartmentService.getAll()
                    .stream()
                    .map(apartmentResponseDtoMapper::mapToDto)
                    .collect(Collectors.toList());
        }
    }

    @GetMapping("/price")
    public List<ApartmentResponseDto> findAllByPriceBetween(@RequestParam BigDecimal from,
                                                            @RequestParam BigDecimal to) {
        return apartmentService.findAllByPriceBetween(from, to)
                .stream()
                .map(apartmentResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/capacity")
    public List<ApartmentResponseDto> findAllByCapacityBetween(@RequestParam Integer from,
                                                               @RequestParam Integer to) {
        return apartmentService.findAllByCapacityBetween(from, to)
                .stream()
                .map(apartmentResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/type")
    public List<ApartmentResponseDto> findAllByApartmentType(@RequestParam String type) {
        ApartmentType apartmentType = apartmentTypeService.findByName(type);
        return apartmentService.findAllByApartmentType(apartmentType)
                .stream()
                .map(apartmentResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by_hotel_id")
    public List<ApartmentResponseDto> findAllByHotelId(@RequestParam Long hotelId) {
        return apartmentService.findAllByHotelId(hotelId).stream()
                .map(apartmentResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/price_desc")
    public List<ApartmentResponseDto> findAllByOrderByPriceDesc() {
        return apartmentService.findAllByOrderByPriceDesc()
                .stream()
                .map(apartmentResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/price_desc_between")
    public List<ApartmentResponseDto> findAllByPriceBetweenAndOrderByPriceDesc(
            @RequestParam BigDecimal from,
            @RequestParam BigDecimal to) {
        return apartmentService.findAllByPriceBetweenOrderByPriceDesc(from, to)
                .stream()
                .map(apartmentResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/price_asc")
    public List<ApartmentResponseDto> findAllByOrderByPriceAsc() {
        return apartmentService.findAllByOrderByPriceAsc()
                .stream()
                .map(apartmentResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/price_asc_between")
    public List<ApartmentResponseDto> findAllByPriceBetweenOrderByPriceAsc(
            @RequestParam BigDecimal from,
            @RequestParam BigDecimal to) {
        return apartmentService.findAllByPriceBetweenOrderByPriceAsc(from, to)
                .stream()
                .map(apartmentResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/available")
    public List<ApartmentResponseDto> findAvailableApartments(
            @RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        return apartmentService.findAvailableApartments(dateFrom, dateTo)
                .stream()
                .map(apartmentResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
