package com.example.travelershub.controller;

import com.example.travelershub.dto.response.RoomResponseDto;
import com.example.travelershub.model.Apartment;
import com.example.travelershub.service.ApartmentService;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = {"*"})
public class ApartmentController {
    private final ApartmentService roomService;
    private final ResponseDtoMapper<RoomResponseDto, Apartment> roomResponseDtoMapper;

    public ApartmentController(ApartmentService roomService, ResponseDtoMapper<RoomResponseDto, Apartment>
            roomResponseDtoMapper) {
        this.roomService = roomService;
        this.roomResponseDtoMapper = roomResponseDtoMapper;
    }
}
