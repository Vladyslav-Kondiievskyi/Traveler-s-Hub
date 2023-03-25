package com.example.travelershub.controller;

import com.example.travelershub.dto.response.RoomResponseDto;
import com.example.travelershub.model.Room;
import com.example.travelershub.service.RoomService;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final ResponseDtoMapper<RoomResponseDto, Room> roomResponseDtoMapper;

    public RoomController(RoomService roomService, ResponseDtoMapper<RoomResponseDto, Room>
            roomResponseDtoMapper) {
        this.roomService = roomService;
        this.roomResponseDtoMapper = roomResponseDtoMapper;
    }
}
