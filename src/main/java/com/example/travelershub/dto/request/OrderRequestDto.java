package com.example.travelershub.dto.request;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private List<Long> apartmentsIds;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
