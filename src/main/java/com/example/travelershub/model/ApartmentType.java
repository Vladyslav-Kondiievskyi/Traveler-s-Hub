package com.example.travelershub.model;

import com.example.travelershub.model.enumfolder.ApartmentKind;
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
@Table(name = "apartment_type")
public class ApartmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private ApartmentKind apartmentKind;

    public ApartmentType() {
    }

    public ApartmentType(ApartmentKind apartmentKind) {
        this.apartmentKind = apartmentKind;
    }
}
