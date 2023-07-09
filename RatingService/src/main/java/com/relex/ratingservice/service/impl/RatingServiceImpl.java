package com.relex.ratingservice.service.impl;

import com.relex.ratingservice.domain.Hotel;
import com.relex.ratingservice.domain.Rating;
import com.relex.ratingservice.exceptions.InvalidDataFormatException;
import com.relex.ratingservice.feignclients.HotelService;
import com.relex.ratingservice.repositories.RatingRepository;
import com.relex.ratingservice.service.RatingService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final HotelService hotelService;
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
//        Получаем от HotelService информацию о отелях
        List<Hotel> hotels = hotelService.getAllHotels();
        List<Object[]> avgRatings = ratingRepository.getAverageRatingsByHotel();
        String csvData = csvBuilder(avgRatings, hotels);
        return csvData;
    }

    //    Составляем csv файл на основе среднего рейтинга отеля и информации о нём
    private String csvBuilder(List<Object[]> avgRatings, List<Hotel> hotels) {
        StringBuilder csvData = new StringBuilder();
        csvData.append("Hotel Name,Address,Average Rating\n");
        for (Hotel hotel : hotels) {
            UUID hotelId = hotel.getHotelId();
            String hotelName = hotel.getName();
            String address = hotel.getLocation();
            String description = hotel.getDescription();
            double averageRating = 0.0;
            for (Object[] ratingData : avgRatings) {
                UUID ratingHotelId = (UUID) ratingData[0];
                double ratingValue = (double) ratingData[1];
                if (hotelId.equals(ratingHotelId)) {
                    averageRating = ratingValue;
                    break;
                }
            }
            csvData.append(hotelName).append(",")
                    .append(address).append(",")
                    .append(averageRating)
                    .append("\n");
        }
        return csvData.toString();
    }
}
