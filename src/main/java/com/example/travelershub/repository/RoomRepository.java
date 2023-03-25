package com.example.travelershub.repository;

import com.example.travelershub.model.Room;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoomRepository extends JpaRepository<Room, Long>,
        JpaSpecificationExecutor<Room> {
    List<Room> findAllByPriceBetween(BigDecimal from, BigDecimal to);
}
