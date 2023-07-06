package com.relex.hotelservice.services;

import com.relex.hotelservice.domain.Hotel;

import java.util.List;
import java.util.UUID;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    Hotel getHotel(UUID id);

    List<Hotel> getAllHotels();
}
