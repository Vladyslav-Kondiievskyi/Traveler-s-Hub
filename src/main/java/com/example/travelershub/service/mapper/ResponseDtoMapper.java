package com.example.travelershub.service.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
