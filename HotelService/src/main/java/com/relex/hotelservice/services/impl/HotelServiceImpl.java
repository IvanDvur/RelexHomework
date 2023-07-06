package com.relex.hotelservice.services.impl;

import com.relex.hotelservice.domain.Hotel;
import com.relex.hotelservice.exceptions.InvalidDataFormatException;
import com.relex.hotelservice.exceptions.ResourceNotFoundException;
import com.relex.hotelservice.repositories.HotelRepository;
import com.relex.hotelservice.services.HotelService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public Hotel saveHotel(Hotel hotel) throws InvalidDataFormatException {
        Set<ConstraintViolation<Hotel>> violations = validator.validate(hotel);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Hotel> violation : violations) {
                System.out.println(violation.getMessage());
            }
            throw new InvalidDataFormatException("Данные имеют некорректный формат");
        }
        return this.hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(UUID id) {
        return this.hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Отель c id " + id + " не найден"));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
