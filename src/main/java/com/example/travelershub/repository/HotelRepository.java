package com.example.travelershub.repository;

import com.example.travelershub.dto.request.FilterRequestDto;
import com.example.travelershub.model.Hotel;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>,
        JpaSpecificationExecutor<Hotel>,BaseRepository<Hotel> {
    List<Hotel> getAllByRatingBetween(Float ratingFrom, Float ratingTo);

    List<Hotel> findAllByRatingIsGreaterThanEqual(Float rating);

    List<Hotel> findAllByStarsIs(Byte stars);

    Hotel findByName(String hotelName);

    List<Hotel> findAllByCity(String city);

    @Query("SELECT h FROM Hotel h JOIN h.reviews r GROUP BY h.id ORDER BY COUNT(r) DESC")
    List<Hotel> findAllOrderByReviewsCountDesc();

    default List<Hotel> findAllByFields(FilterRequestDto filterRequestDto) {
        Specification<Hotel> spec = hasFields(filterRequestDto);
        return findAll(spec);
    }
}
