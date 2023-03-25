package com.example.travelershub.service;

import com.example.travelershub.model.Room;
import java.math.BigDecimal;
import java.util.List;

public interface RoomService {

    Room save(Room room);

    Room getById(Long id);

    Room update(Room room);

    void deleteById(Long id);

    List<Room> findAllByPriceBetween(BigDecimal from, BigDecimal to);
}
