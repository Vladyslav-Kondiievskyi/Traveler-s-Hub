package com.example.travelershub.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

//todo Vladyslav - Room, User. DTO, repository, service
@Getter
@Setter
@Entity(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private BigDecimal price;
    private int capacity;
    @OneToOne
    private Hotel hotel;
    @ElementCollection
    private List<String> pictures;
}
