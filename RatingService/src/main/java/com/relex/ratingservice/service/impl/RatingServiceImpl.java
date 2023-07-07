package com.relex.ratingservice.service.impl;

import com.relex.ratingservice.domain.Hotel;
import com.relex.ratingservice.domain.Rating;
import com.relex.ratingservice.exceptions.InvalidDataFormatException;
import com.relex.ratingservice.repositories.RatingRepository;
import com.relex.ratingservice.service.RatingService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    @Value("${fetchAllHotelsURL}")
    private String fetchAllHotelsUrl;

    private final RestTemplate restTemplate;
    private final RatingRepository ratingRepository;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public Rating createRating(Rating rating) {
        Set<ConstraintViolation<Rating>> violations = validator.validate(rating);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Rating> violation : violations) {
                System.out.println(violation.getMessage());
            }
            throw new InvalidDataFormatException("Данные имеют некорректный формат");
        }
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllByUser(UUID userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllByHotel(UUID hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public String getAverageHotelRatings() {
        StringBuffer sb = new StringBuffer();
        ResponseEntity<List<Hotel>> responseEntity = restTemplate.exchange(
                fetchAllHotelsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Hotel>>() {
                }
        );
        List<Object[]> avgRatings = ratingRepository.getAverageRatingsByHotel();
        List<Hotel> hotels = responseEntity.getBody();

        avgRatings.forEach(x-> System.out.println(Arrays.toString(x)));
        return "";
    }
}
