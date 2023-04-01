package com.example.travelershub.service.mapper;

import com.example.travelershub.dto.request.OrderRequestDto;
import com.example.travelershub.dto.response.OrderResponseDto;
import com.example.travelershub.model.Apartment;
import com.example.travelershub.model.Order;
import com.example.travelershub.service.ApartmentService;
import com.example.travelershub.service.UserService;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements ResponseDtoMapper<OrderResponseDto, Order>,
        RequestDtoMapper<OrderRequestDto, Order> {
    private final UserService userService;
    private final ApartmentService apartmentService;

    public OrderMapper(UserService userService, ApartmentService apartmentService) {
        this.userService = userService;
        this.apartmentService = apartmentService;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setApartmentsIds(order.getApartments().stream()
                .map(Apartment::getId)
                .collect(Collectors.toList()));
        dto.setClientId(order.getClient().getId());
        dto.setAmount(order.getAmount());
        dto.setDateFrom(order.getDateFrom());
        dto.setDateTo(order.getDateTo());
        return dto;
    }

    @Override
    public Order mapToModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setApartments(dto.getApartmentsIds().stream()
                .map(apartmentService::getById)
                .collect(Collectors.toList()));
        order.setDateFrom(dto.getDateFrom());
        order.setDateTo(dto.getDateTo());
        BigDecimal bookedDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(order.getDateFrom(), order.getDateTo()));
        BigDecimal totalAmountPerDay = order.getApartments().stream()
                .map(Apartment::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setAmount(totalAmountPerDay.multiply(bookedDays));
        return order;
    }
}
