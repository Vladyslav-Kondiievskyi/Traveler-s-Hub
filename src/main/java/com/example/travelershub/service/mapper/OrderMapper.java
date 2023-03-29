package com.example.travelershub.service.mapper;

import com.example.travelershub.dto.response.OrderResponseDto;
import com.example.travelershub.model.Apartment;
import com.example.travelershub.model.Order;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements ResponseDtoMapper<OrderResponseDto, Order> {

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setRoomsId(order.getApartments().stream()
                .map(Apartment::getId)
                .collect(Collectors.toList()));
        dto.setClientId(order.getClient().getId());
        dto.setAmount(order.getAmount());
        dto.setDateFrom(order.getDateFrom());
        dto.setDateTo(order.getDateTo());
        return dto;
    }
}
