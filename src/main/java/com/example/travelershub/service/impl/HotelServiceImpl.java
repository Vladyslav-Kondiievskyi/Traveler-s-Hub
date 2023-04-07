package com.example.travelershub.service.impl;

import com.example.travelershub.dto.request.HotelRequestDto;
import com.example.travelershub.dto.request.filter.FilterRequest;
import com.example.travelershub.model.Apartment;
import com.example.travelershub.model.Hotel;
import com.example.travelershub.repository.HotelRepository;
import com.example.travelershub.service.HotelService;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getById(Long id) {
        return hotelRepository.findById(id).get();
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public List<Hotel> getAllByRatingBetween(Float ratingFrom, Float ratingTo) {
        return hotelRepository.getAllByRatingBetween(ratingFrom, ratingTo);
    }

    @Override
    public List<Hotel> findAllByRatingIsGreaterThan(Float rating) {
        return hotelRepository.findAllByRatingIsGreaterThanEqual(rating);
    }

    @Override
    public List<Hotel> findAllByStarsIs(Byte stars) {
        return hotelRepository.findAllByStarsIs(stars);
    }

    @Override
    public Hotel findByName(String hotelName) {
        return hotelRepository.findByName(hotelName);
    }

    @Override
    public List<Hotel> findAllByCity(String city) {
        return hotelRepository.findAllByCity(city);
    }

    @Override
    public List<Hotel> findAllOrderByReviewsCountDesc() {
        return hotelRepository.findAllOrderByReviewsCountDesc();
    }

    @Override
    public Set<String> getMainAmenities(List<Apartment> rooms) {
        return rooms.stream()
                .flatMap(room -> room.getAmenities().stream())
                .distinct()
                .limit(4)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public List<HotelRequestDto> sortHotels(List<HotelRequestDto> hotels, String sortBy) {
        boolean ascendingOrder = true;
        if (sortBy.endsWith(" desc")) {
            ascendingOrder = false;
            sortBy = sortBy.substring(0, sortBy.length() - 5).trim();
        } else if (sortBy.endsWith(" asc")) {
            sortBy = sortBy.substring(0, sortBy.length() - 4).trim();
        }
        switch (sortBy) {
            case "price":
                hotels.sort(Comparator.comparing(hotel -> hotel.getPrice()));
                break;
            case "recommended":
                hotels.sort(Comparator.comparingInt(hotel -> Math.toIntExact(hotel.getAllReviews())));
                break;
            case "stars":
                hotels.sort(Comparator.comparing(value -> value.getStars()));
                break;
            case "rating":
                hotels.sort(Comparator.comparing(hotel -> hotel.getRating()));
                break;
            default:
                throw new IllegalArgumentException("Unknown sort option: " + sortBy);
        }
        if (!ascendingOrder) {
            Collections.reverse(hotels);
        }
        return hotels;
    }

    @Override
    public List<Hotel> filterHotels(FilterRequest filters) {
        Specification<Hotel> spec = buildSpecification(filters);
        List<Hotel> filteredHotels = hotelRepository.findAll(spec);
        filterApartmentsByPriceRange(filters.getPriceMin(), filters.getPriceMax(), filteredHotels);
        return filteredHotels;
    }

    private Specification<Hotel> buildSpecification(FilterRequest filters) {
        return Specification.where(hotelRepository.byCity(filters.getCity()))
                .and(hotelRepository.byName(filters.getName()))
                .and(hotelRepository.byRating(filters.getRating()))
                .and(hotelRepository.byStars(filters.getStars()))
                .and(hotelRepository.byAmenities(filters.getAmenities()))
                .and(hotelRepository.byApartmentType(filters.getApartmentType()))
                .and(hotelRepository.byCapacity(filters.getCapacity()))
                .and(hotelRepository.byPriceRange(filters.getPriceMin(), filters.getPriceMax()))
                .and(hotelRepository.isAvailable(filters.getDateFrom(), filters.getDateTo()));
    }

    private void filterApartmentsByPriceRange(BigDecimal priceMin, BigDecimal priceMax, List<Hotel> hotels) {
        for (Hotel hotel : hotels) {
            List<Apartment> apartments = hotel.getRooms();
            int minPriceIndex = findMinPriceIndex(priceMin, priceMax, apartments);
            if (minPriceIndex != -1) {
                Apartment minPriceApartment = apartments.get(minPriceIndex);
                apartments.set(minPriceIndex, apartments.get(0));
                apartments.set(0, minPriceApartment);
            }
        }
    }

    private int findMinPriceIndex(BigDecimal priceMin, BigDecimal priceMax, List<Apartment> apartments) {
        for (int i = 0; i < apartments.size(); i++) {
            Apartment apartment = apartments.get(i);
            if (apartment.getPrice().compareTo(priceMin) >= 0
                    && apartment.getPrice().compareTo(priceMax) <= 0) {
                return i;
            }
        }
        return -1;
    }
}
