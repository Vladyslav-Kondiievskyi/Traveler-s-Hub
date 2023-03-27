package com.example.travelershub.controller;

import com.example.travelershub.dto.request.ReviewRequestDto;
import com.example.travelershub.dto.response.HotelResponseDto;
import com.example.travelershub.model.Amenity;
import com.example.travelershub.model.Hotel;
import com.example.travelershub.model.Review;
import com.example.travelershub.model.Room;
import com.example.travelershub.service.AmenityService;
import com.example.travelershub.service.HotelService;
import com.example.travelershub.service.ReviewService;
import com.example.travelershub.service.RoomService;
import com.example.travelershub.service.mapper.RequestDtoMapper;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;
    private final RoomService roomService;
    private final ReviewService reviewService;
    private final AmenityService amenityService;

    private final ResponseDtoMapper<HotelResponseDto, Hotel> hotelResponseDtoMapper;

    private final RequestDtoMapper<ReviewRequestDto, Review> reviewRequestDtoMapper;

    public HotelController(HotelService hotelService, RoomService roomService,
                           ReviewService reviewService, AmenityService amenityService,
                           ResponseDtoMapper<HotelResponseDto, Hotel> hotelResponseDtoMapper,
                           RequestDtoMapper<ReviewRequestDto, Review> reviewRequestDtoMapper) {
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.reviewService = reviewService;
        this.amenityService = amenityService;
        this.hotelResponseDtoMapper = hotelResponseDtoMapper;
        this.reviewRequestDtoMapper = reviewRequestDtoMapper;
    }

    @GetMapping("/all")
    public List<HotelResponseDto> getAll() {
        return hotelService.getAll().stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/min_rating{rating}")
    public List<HotelResponseDto> findAllByRatingIsGreaterThan(@PathVariable BigDecimal rating) {
        return hotelService.findAllByRatingIsGreaterThan(rating)
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public HotelResponseDto addReview(@PathVariable Long id,
                                      @RequestBody ReviewRequestDto reviewRequestDto) {
        reviewService.save(reviewRequestDtoMapper.mapToModel(reviewRequestDto));
        return hotelResponseDtoMapper.mapToDto(hotelService.addReview(id,reviewRequestDtoMapper
                .mapToModel(reviewRequestDto)));
    }

    @GetMapping("/rating")
    public List<HotelResponseDto> findAllByRatingBetween(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to) {
        return hotelService.getAllByRatingBetween(from, to)
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/amenities/{amenities}")
    public List<Hotel> getHotelsByAmenities(@PathVariable List<Amenity.AmenityName> amenities) {
        return hotelService.findHotelByAmenities(amenities);
    }

    @PostMapping("/{hotelId}/reviews")
    public ResponseEntity<String> addReviewToHotel(@PathVariable Long hotelId,
                                                   @RequestBody Review review) {
        hotelService.addReviewToHotel(hotelId, review);
        return ResponseEntity.ok("Review added to hotel with id " + hotelId);
    }

    @GetMapping("/inject")
    public String inject(@RequestParam(defaultValue = "5") Integer count) {
        int maxRooms = 30;
        BigDecimal[] prices = {BigDecimal.valueOf(100),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(300),
                BigDecimal.valueOf(400),
                BigDecimal.valueOf(500)};
        int [] capacities = {1, 2, 3, 4, 5};
        List<String> pictureUrl = List.of("https://www.ahstatic.com/photos/1276_ho_00_p_1024x768.jpg",
                "https://www.murhotels.com/cache/40/b3/40b3566310d686be665d9775f59ca9cd.jpg",
                "https://www.h-hotels.com/_Resources/Persistent/0/1/a/4/01a400d0047f4b7599631797fc27ceabf9e68db3/aussenansicht-nacht-03-h4-hotel-berlin-alexanderplatz-2400x1113.jpg",
                "https://media.radissonhotels.net/image/metropolitan-hotel-sofia-a-member-of-radisson-individuals/exteriorview/16256-145921-f72742573_3xl.jpg?impolicy=Card&gravity=North",
                "https://www.althoffcollection.com/fileadmin/_processed_/8/3/csm_althoff-collection-hotel-am-schlossgarten-aussenansicht-rendering-umbau-tag-web_67cee1fd7f.jpg");
        Amenity[] amenities = {
                new Amenity(Amenity.AmenityName.WIFI),
                new Amenity(Amenity.AmenityName.PARKING),
                new Amenity(Amenity.AmenityName.POOL),
                new Amenity(Amenity.AmenityName.GYM),
                new Amenity(Amenity.AmenityName.SPA),
                new Amenity(Amenity.AmenityName.RESTAURANT)
        };
        amenityService.saveAll(List.of(amenities));
        String[] addresses = {"123 Main St", "456 1st Ave", "789 Oak Ln", "321 Pine St", "654 Elm Ave", "987 Maple Blvd", "741 Cherry Ave", "852 Cedar St", "963 Oak St", "369 Walnut Blvd"};
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Philadelphia", "Phoenix", "San Antonio", "San Diego", "Dallas", "San Jose"};
        String[] telephones = {"(123)456-7890", "(234)567-8901", "(345)678-9012", "(456)789-0123", "(567)890-1234", "(678)901-2345", "(789)012-3456", "(890)123-4567", "(901)234-5678", "(012)345-6789"};
        String[] names = {"The Plaza Hotel", "The Ritz-Carlton", "The Four Seasons", "The Waldorf Astoria", "The St. Regis", "The Mandarin Oriental", "The Peninsula", "The W Hotel", "The Langham", "The Raffles Hotel"};
        String[] descriptions = {"Luxury hotel in the heart of the city", "5-star accommodations with spectacular views", "World-renowned hotel with top-notch amenities", "Iconic landmark offering unparalleled service", "Exquisite hotel with a rich history", "Exclusive retreat for the discerning traveler", "Opulent accommodations in a prime location", "Cutting-edge hotel with a modern vibe", "Elegant property with a classic feel", "Unmatched hospitality in a stunning setting"};
        BigDecimal[] ratings = {BigDecimal.valueOf(4.9), BigDecimal.valueOf(4.8), BigDecimal.valueOf(4.7), BigDecimal.valueOf(4.6), BigDecimal.valueOf(4.5), BigDecimal.valueOf(4.4), BigDecimal.valueOf(4.3), BigDecimal.valueOf(4.2), BigDecimal.valueOf(4.1), BigDecimal.valueOf(4.0)};
        Integer[] stars = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

        for (int i = 0; i < count; i++) {
            Hotel hotel = new Hotel();
            hotel.setName(names[new Random().nextInt(names.length)]);
            Set<Amenity> amenitySet = new HashSet<>();
            for (int j = 0; j < amenities.length; j++) {
                if (new Random().nextInt(10) % 2 == 0) {
                    amenitySet.add(amenities[i]);
                }
            }
            hotel.setAmenities(amenitySet);
            hotel.setPicturesUrl(Collections.singletonList(pictureUrl.get(i)));
            hotel.setAddress(addresses[i]);
            hotel.setCity(cities[i]);
            hotel.setTelephone(telephones[i]);
            hotel.setDescription(descriptions[i]);
            hotel.setRating(ratings[i]);
            hotel.setStars(stars[i]);
            List<Room> rooms = new ArrayList<>();
            for (int k = 0; k < maxRooms; k++) {
                Room room = new Room();
                room.setNumber(k + 1);
                room.setPrice(prices[new Random().nextInt(prices.length)]);
                room.setCapacity(capacities[new Random().nextInt(capacities.length)]);
                room.setPicturesUrl(pictureUrl);
                rooms.add(room);
                roomService.save(room);
            }
            hotel.setRooms(rooms);
            hotelService.save(hotel);
        }
        return "Create " + count + " hotels";
    }
}