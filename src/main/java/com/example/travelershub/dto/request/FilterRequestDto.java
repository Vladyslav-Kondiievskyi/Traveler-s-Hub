package com.example.travelershub.dto.request;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterRequestDto {
    private Map<String, Object> filters;
    private Map<String, String> sort;
    private int pageNumber;
    private int pageSize;

}
