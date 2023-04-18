package com.example.travelershub.service;

import com.example.travelershub.model.Order;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order getById(Long id);

    List<Order> findAllByClientIdAndConfirmIsFalse(Long clientId);

    Order confirmOrder(Order order);
}
