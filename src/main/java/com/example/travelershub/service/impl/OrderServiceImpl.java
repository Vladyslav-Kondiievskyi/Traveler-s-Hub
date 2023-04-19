package com.example.travelershub.service.impl;

import com.example.travelershub.model.Order;
import com.example.travelershub.repository.OrderRepository;
import com.example.travelershub.service.OrderService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> findAllByClientIdAndConfirmIsFalse(Long clientId) {
        return orderRepository.findAllByClientIdAndConfirmIsFalse(clientId);
    }

    @Override
    public Order confirmOrder(Order order) {
        Order orderFromDb = orderRepository.findById(order.getId()).get();
        orderFromDb.setConfirm(true);
        orderRepository.save(orderFromDb);
        return orderFromDb;
    }
}
