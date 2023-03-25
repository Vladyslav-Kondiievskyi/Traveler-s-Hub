package com.example.travelershub.service.impl;

import com.example.travelershub.model.Room;
import com.example.travelershub.repository.RoomRepository;
import com.example.travelershub.service.RoomService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getById(Long id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public Room update(Room room) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return roomRepository.findAllByPriceBetween(from, to);
    }
}
