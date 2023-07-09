package com.relex.userservice.feignclients;

import com.relex.userservice.domain.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


// Внешний feign client для обращения к hotel-service
@FeignClient(name = "hotel-service")
@Service
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable ("hotelId") String hotelId);


}
