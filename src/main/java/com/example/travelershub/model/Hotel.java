package com.example.travelershub.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
//todo by Kostiantyn - Hotel, Order. DTO, repository, service

@Getter
@Setter
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Room> rooms;
    private String address;
    private String city;
    private String telephone;
    private String name;
    @ElementCollection
    private List<String> pictures;
    private BigDecimal rating;
    private String description;
    @ManyToMany
    private List<Amenity> amenities;
}
