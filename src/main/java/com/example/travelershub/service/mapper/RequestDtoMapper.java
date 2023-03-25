package com.example.travelershub.service.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
