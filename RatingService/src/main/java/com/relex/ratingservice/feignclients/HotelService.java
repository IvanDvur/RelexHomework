package com.relex.ratingservice.feignclients;

import com.relex.ratingservice.domain.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "hotel-service")
@Service
public interface HotelService {

    @GetMapping("/hotels")
    List<Hotel> getAllHotels();
}
