package com.example.travelershub.controller;

import com.example.travelershub.dto.request.OrderRequestDto;
import com.example.travelershub.dto.response.OrderResponseDto;
import com.example.travelershub.model.Order;
import com.example.travelershub.model.User;
import com.example.travelershub.service.OrderService;
import com.example.travelershub.service.UserService;
import com.example.travelershub.service.mapper.RequestDtoMapper;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final RequestDtoMapper<OrderRequestDto, Order> requestDtoMapper;
    private final ResponseDtoMapper<OrderResponseDto, Order> responseDtoMapper;

    public OrderController(OrderService orderService, UserService userService, RequestDtoMapper<OrderRequestDto, Order> requestDtoMapper, ResponseDtoMapper<OrderResponseDto, Order> responseDtoMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping("/complete")
    OrderResponseDto completeOrder(Authentication auth, @RequestBody OrderRequestDto requestDto) {
        String email = auth.getName();
        User user = userService.findByEmail(email).get();
        Order order = requestDtoMapper.mapToModel(requestDto);
        order.setClient(user);
        orderService.save(order);
        return responseDtoMapper.mapToDto(order);
    }
}
