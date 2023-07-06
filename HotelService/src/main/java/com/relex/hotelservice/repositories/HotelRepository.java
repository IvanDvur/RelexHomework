package com.relex.hotelservice.repositories;

import com.relex.hotelservice.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {
}
