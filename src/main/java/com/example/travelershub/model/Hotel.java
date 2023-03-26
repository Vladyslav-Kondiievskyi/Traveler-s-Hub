package com.example.travelershub.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
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
    @JoinTable(name = "hotels_rooms",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "rooms_id"))
    private List<Room> rooms;
    @OneToMany
    @JoinTable(name = "hotels_reviews",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id"))
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
    @ManyToMany
    private Set<Amenity> amenities;
    @ElementCollection
    private List<String> picturesUrl;
    private String address;
    private String city;
    private String telephone;
    private String name;
    private String description;
    private BigDecimal rating;
    private Integer stars;
}
