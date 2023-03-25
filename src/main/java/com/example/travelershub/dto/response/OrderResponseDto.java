package com.example.travelershub.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private List<Long> roomsId;
    private Long clientId;
    private BigDecimal amount;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
