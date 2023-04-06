package com.example.travelershub.repository;

import com.example.travelershub.model.Apartment;
import com.example.travelershub.model.Hotel;
import com.example.travelershub.model.Order;
import com.example.travelershub.model.Review;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public interface HotelSpecification {
    default Specification<Hotel> byCity(String city) {
        return (root, query, criteriaBuilder) -> {
            if (city == null || city.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            query.distinct(true);
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("city")),
                    "%" + city.toLowerCase() + "%");
        };
    }

    default Specification<Hotel> byReviews(Long reviews) {
        return (root, query, criteriaBuilder) -> {
            if (reviews == null) {
                return criteriaBuilder.conjunction();
            }
            query.distinct(true);
            Join<Hotel, Review> join = root.join("review", JoinType.LEFT);
            return criteriaBuilder.ge(criteriaBuilder.count(join.get("id")), reviews);
        };
    }

    default Specification<Hotel> byName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            query.distinct(true);
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%");
        };
    }

    default Specification<Hotel> byRating(Float rating) {
        return (root, query, criteriaBuilder) -> {
            if (rating == null) {
                return criteriaBuilder.conjunction();
            }
            query.distinct(true);
            return criteriaBuilder.ge(root.get("rating"), rating);
        };
    }

    default Specification<Hotel> byStars(Byte stars) {
        return (root, query, criteriaBuilder) -> {
            if (stars == null) {
                return criteriaBuilder.conjunction();
            }
            query.distinct(true);
            return criteriaBuilder.ge(root.get("stars"), stars);
        };
    }

    default Specification<Hotel> byAmenities(Set<String> amenities) {
        return (root, query, criteriaBuilder) -> {
            if (amenities == null || amenities.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Hotel, Apartment> apartmentJoin = root.join("rooms", JoinType.INNER);
            Join<Apartment, String> amenitiesJoin = apartmentJoin.join("amenities", JoinType.INNER);
            query.distinct(true);
            return amenitiesJoin.in(amenities);
        };
    }

    default Specification<Hotel> byApartmentType(List<String> apartmentTypes) {
        return (root, query, criteriaBuilder) -> {
            if (apartmentTypes == null || apartmentTypes.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Hotel, Apartment> apartmentJoin = root.join("rooms", JoinType.INNER);
            query.distinct(true);
            return apartmentJoin.get("apartmentTypeId").in(apartmentTypes);
        };
    }

    default Specification<Hotel> byCapacity(Integer capacity) {
        return (root, query, criteriaBuilder) -> {
            if (capacity == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Hotel, Apartment> apartmentJoin = root.join("rooms", JoinType.INNER);
            Predicate byCapacity = criteriaBuilder.equal(apartmentJoin.get("capacity"), capacity);
            query.distinct(true);
            return byCapacity;
        };
    }

    default Specification<Hotel> isAvailable(LocalDate dateFrom, LocalDate dateTo) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Order> orderSubQuery = query.subquery(Order.class);
            Root<Order> orderRoot = orderSubQuery.from(Order.class);
            Join<Order, Apartment> apartmentJoin = orderRoot.join("apartments");
            orderSubQuery.select(orderRoot.get("id"))
                    .where(criteriaBuilder.and(
                            criteriaBuilder.lessThanOrEqualTo(orderRoot.get("dateFrom"), dateTo),
                            criteriaBuilder.greaterThanOrEqualTo(orderRoot.get("dateTo"), dateFrom),
                            criteriaBuilder.equal(apartmentJoin.get("id"), root.get("id"))
                    ));
            Predicate availabilityPredicate = criteriaBuilder.not(criteriaBuilder.exists(orderSubQuery));
            query.distinct(true);
            return availabilityPredicate;
        };
    }

    default Specification<Hotel> byPriceRange(BigDecimal priceMin, BigDecimal priceMax) {
        return (root, query, criteriaBuilder) -> {
            if (priceMin == null || priceMax == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Hotel, Apartment> apartmentJoin = root.join("rooms", JoinType.INNER);
            query.distinct(true);
            return criteriaBuilder.between(apartmentJoin.get("price"), priceMin, priceMax);
        };
    }
}
