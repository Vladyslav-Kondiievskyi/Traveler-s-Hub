package com.example.travelershub.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "amenities")
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private AmenityName name;

    public Amenity() {
    }

    public Amenity(AmenityName name) {
        this.name = name;
    }

    public enum AmenityName {
        WIFI,
        PARKING,
        POOL,
        GYM,
        SPA,
        RESTAURANT;
    }
}
