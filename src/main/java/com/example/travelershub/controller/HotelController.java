package com.example.travelershub.controller;

import com.example.travelershub.dto.request.FilterRequest;
import com.example.travelershub.dto.request.HotelRequestDto;
import com.example.travelershub.dto.response.HotelResponseDto;
import com.example.travelershub.model.Apartment;
import com.example.travelershub.model.ApartmentType;
import com.example.travelershub.model.Hotel;
import com.example.travelershub.model.enumfolder.ApartmentKind;
import com.example.travelershub.service.ApartmentService;
import com.example.travelershub.service.ApartmentTypeService;
import com.example.travelershub.service.HotelService;
import com.example.travelershub.service.mapper.ResponseDtoMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
@CrossOrigin(origins = {"*", "https://innjoy.space"}, allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class HotelController {
    private final HotelService hotelService;
    private final ApartmentService roomService;
    private final ApartmentTypeService apartmentTypeService;
    private final ResponseDtoMapper<HotelResponseDto, Hotel> hotelResponseDtoMapper;

    public HotelController(HotelService hotelService,
                           ApartmentService roomService,
                           ApartmentTypeService apartmentTypeService,
                           ResponseDtoMapper<HotelResponseDto, Hotel> hotelResponseDtoMapper) {
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.apartmentTypeService = apartmentTypeService;
        this.hotelResponseDtoMapper = hotelResponseDtoMapper;
    }

    @GetMapping("/all")
    public List<HotelResponseDto> getAll() {
        return hotelService.getAll().stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<HotelResponseDto> findAllByRatingIsGreaterThan(@RequestParam Float minRating) {
        return hotelService.findAllByRatingIsGreaterThan(minRating)
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/rating")
    public List<HotelResponseDto> findAllByRatingBetween(@RequestParam Float from,
                                                         @RequestParam Float to) {
        return hotelService.getAllByRatingBetween(from, to)
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/stars")
    public List<HotelResponseDto> findAllByStarEqual(@RequestParam Byte star) {
        return hotelService.findAllByStarsIs(star)
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/name")
    public HotelResponseDto findByName(@RequestParam String hotelName) {
        return hotelResponseDtoMapper.mapToDto(hotelService.findByName(hotelName));
    }

    @GetMapping("/city")
    public List<HotelResponseDto> findAllByCity(@RequestParam String city) {
        return hotelService.findAllByCity(city)
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping ("/sort")
    public List<HotelRequestDto> sortByAllParameter(@RequestBody List<HotelRequestDto> dto,@RequestParam String sortBy) {
        return hotelService.sortHotels(dto,sortBy);
    }

    @GetMapping("/filters")
    public List<HotelResponseDto> findAllByFilter(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Float rating,
            @RequestParam(required = false) Byte stars,
            @RequestParam(required = false, defaultValue = "0") BigDecimal priceMin,
            @RequestParam(required = false, defaultValue = "1000000") BigDecimal priceMax,
            @RequestParam(required = false) Integer capacity,
            @RequestParam(required = false) Set<String> amenities,
            @RequestParam(required = false) List<String> apartmentType,
            @RequestParam(required = false,name = "dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false,name = "dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
            @RequestParam(required = false) String sort) {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setCity(city);
        filterRequest.setName(name);
        filterRequest.setRating(rating);
        filterRequest.setStars(stars);
        filterRequest.setDateFrom(dateFrom);
        filterRequest.setDateTo(dateTo);
        filterRequest.setSort(sort);
        filterRequest.setCapacity(capacity);
        filterRequest.setAmenities(amenities);
        filterRequest.setApartmentType(apartmentType);
        filterRequest.setPriceMin(priceMin);
        filterRequest.setPriceMax(priceMax);
        filterRequest.setSort(sort);
        return hotelService.filterHotels(filterRequest)
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/orders_desc")
    public List<HotelResponseDto> findAllOrderByReviewsCountDesc() {
        return hotelService.findAllOrderByReviewsCountDesc()
                .stream()
                .map(hotelResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/sort_reviews_by_hotel{hotelId}")
    public Map<Float, Integer> getReviewMapByHotelId(@PathVariable Long hotelId) {
        return hotelService.countReviewsByRating(hotelId);
    }

    @GetMapping("/inject")
    public String inject(@RequestParam(defaultValue = "5") Integer count) {
        int maxRooms = 30;
        BigDecimal[] prices = {BigDecimal.valueOf(100),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(300),
                BigDecimal.valueOf(400),
                BigDecimal.valueOf(500)};
        int[] capacities = {1, 2, 3, 4, 5};
        List<String> pictureUrl = List.of(
                "https://www.ahstatic.com/photos/1276_ho_00_p_1024x768.jpg",
                "https://www.murhotels.com/cach"
                        + "e/40/b3/40b3566310d686be665d9775f59ca9cd.jpg",
                "https://www.h-hotels.com/_Resources/Persistent/0/1/a/4/01"
                        + "a400d0047f4b7599631797fc27ceabf9e68db3/aussenansicht"
                        + "-nacht-03-h4-hotel-berlin-alexanderplatz-2400x1113.jpg",
                "https://media.radissonhotels.net/image/metropolitan-hotel-s"
                        + "ofia-a-member-of-radisson-individuals/exteriorview/162"
                        + "56-145921-f72742573_3xl.jpg?impolicy=Card&gravity=North",
                "https://www.althoffcollection.com/fileadmin/_processed_/8/3/cs"
                        + "m_althoff-collection-hotel-am-schlossgarten-aussenansic"
                        + "ht-rendering-umbau-tag-web_67cee1fd7f.jpg");
        String[] amenities = {"WIFI", "PARKING", "POOL", "GYM", "SPA", "RESTAURANT"};
        String[] addresses = {"1919 Madison Ave",
                "503 Foothill Rd, Beverly Hills", "4248 S Michigan Ave", "2712 Blodgett St", "2600 Benjamin Franklin Pkwy",
                "4725 E Mayo Blvd", "17000 W I-10", "1500 Orange Ave",
                "2201 N Field St", "377 Santana Row"};
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Philadelphia",
                "Phoenix", "San Antonio", "San Diego", "Dallas", "San Jose"};
        String[] telephones = {"(123)456-7890", "(234)567-8901", "(345)678-9012",
                "(456)789-0123", "(567)890-1234", "(678)901-2345", "(789)012-3456",
                "(890)123-4567", "(901)234-5678", "(012)345-6789"};
        String[] names = {"The Plaza Hotel", "The Ritz-Carlton", "The Four Seasons",
                "The Waldorf Astoria", "The St. Regis", "The Mandarin Oriental",
                "The Peninsula", "The W Hotel", "The Langham", "The Raffles Hotel"};
        String[] descriptions = {"Luxury hotel in the heart of the city",
                "5-star accommodations with spectacular views",
                "World-renowned hotel with top-notch amenities",
                "Iconic landmark offering unparalleled service",
                "Exquisite hotel with a rich history",
                "Exclusive retreat for the discerning traveler",
                "Opulent accommodations in a prime location",
                "Cutting-edge hotel with a modern vibe",
                "Elegant property with a classic feel",
                "Unmatched hospitality in a stunning setting"};
        Float[] ratings = {4.9f, 4.8f, 4.7f, 4.6f, 4.5f, 4.4f, 4.3f, 4.2f, 4.1f, 4.0f};
        Byte[] stars = {5, 3, 4, 5, 3, 2, 4, 3, 5, 5};
        ApartmentType[] apartmentTypes = {new ApartmentType(ApartmentKind.APARTMENT),
                new ApartmentType(ApartmentKind.BED),
                new ApartmentType(ApartmentKind.ROOM), new ApartmentType(ApartmentKind.HOUSE),
                new ApartmentType(ApartmentKind.VILLA)};
        apartmentTypeService.saveAll(List.of(apartmentTypes));
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Hotel hotel = new Hotel();
            hotel.setName(names[new Random().nextInt(names.length)]);
            Set<String> amenitySet = new HashSet<>();
            for (int j = 0; j < amenities.length; j++) {
                if (new Random().nextInt(10) % 2 == 0) {
                    amenitySet.add(amenities[i]);
                }
            }
            hotel.setPicturesUrl(Collections.singletonList(pictureUrl.get(i)));
            hotel.setAddress(addresses[i]);
            hotel.setCity(cities[i]);
            hotel.setTelephone(telephones[i]);
            hotel.setDescription(descriptions[i]);
            hotel.setRating(ratings[i]);
            hotel.setStars(stars[i]);
            List<Apartment> rooms = new ArrayList<>();
            hotelService.save(hotel);
            for (int k = 0; k < maxRooms; k++) {
                Apartment room = new Apartment();
                room.setNumber(k + 1);
                room.setPrice(prices[random.nextInt(prices.length)]);
                room.setCapacity(capacities[random.nextInt(capacities.length)]);
                int firstIndex = random.nextInt(amenities.length);
                int secondIndex = random.nextInt(amenities.length - 1);
                if (secondIndex >= firstIndex) {
                    secondIndex++;
                }
                room.setAmenities(Set.of(amenities[firstIndex], amenities[secondIndex]));
                room.setApartmentType(apartmentTypes[random.nextInt(apartmentTypes.length)]);
                room.setHotel(hotel);
                rooms.add(room);
                roomService.save(room);
            }
            hotel.setRooms(rooms);
        }
        return "Create " + count + " hotels";
    }
}
