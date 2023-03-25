package com.example.travelershub.service;

import com.example.travelershub.model.Order;
import com.example.travelershub.model.User;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order getById(Long id);

    Order update(Order order);

    List<Order> findAllByClient(User client);

    void deleteById(Long id);
}
