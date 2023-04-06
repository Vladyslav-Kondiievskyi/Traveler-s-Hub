package com.example.travelershub.model;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Apartment> rooms;
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private List<Review> reviews;
    @ElementCollection
    private List<String> picturesUrl;
    private String address;
    private String city;
    private String telephone;
    private String name;
    private String description;
    private Float rating;
    private Byte stars;
}
