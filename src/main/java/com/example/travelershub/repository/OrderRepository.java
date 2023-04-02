package com.example.travelershub.repository;

import com.example.travelershub.model.Order;
import com.example.travelershub.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>,
        JpaSpecificationExecutor<Order> {
    List<Order> findAllByClient(User client);

    @Query("SELECT o FROM Order o where o.client.id = :clientId AND o.isConfirm = false")
    List<Order> findAllByClientIdAndConfirmIsFalse(@Param("clientId") Long clientId);
}
