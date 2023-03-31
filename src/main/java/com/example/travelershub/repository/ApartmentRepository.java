package com.example.travelershub.repository;

import com.example.travelershub.model.Apartment;
import com.example.travelershub.model.ApartmentType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long>,
        JpaSpecificationExecutor<Apartment> {
    List<Apartment> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    @Query("SELECT a FROM Apartment a JOIN a.amenities amenity "
            + "WHERE amenity IN :amenities "
            + "GROUP BY a "
            + "HAVING COUNT(DISTINCT amenity) = :amenitiesSize")
    List<Apartment> findByAmenitiesIn(Set<String> amenities, @Param("amenitiesSize") long amenitiesSize);

    @Query("FROM Apartment a WHERE NOT EXISTS "
            + "(SELECT o.id FROM Order o JOIN o.apartments oa WHERE oa.id = a.id AND "
            + "((o.dateFrom <= :dateTo) AND (o.dateTo >= :dateFrom)))")
    List<Apartment> findAvailableApartments(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo);

    List<Apartment> findAllByCapacityBetween(Integer from, Integer to);

    List<Apartment> findAllByApartmentType(ApartmentType apartmentType);

    List<Apartment> findAllByHotelId(Long hotelId);

    List<Apartment> findAllByOrderByPriceDesc();

    List<Apartment> findAllByOrderByPriceAsc();

    List<Apartment> findAllByPriceBetweenOrderByPriceAsc(BigDecimal from, BigDecimal to);

    List<Apartment> findAllByPriceBetweenOrderByPriceDesc(BigDecimal from, BigDecimal to);
}
