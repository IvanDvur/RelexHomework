package com.relex.ratingservice.service;

import com.relex.ratingservice.domain.Rating;

import java.util.List;
import java.util.UUID;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getAllByUser(UUID userId);

    List<Rating> getAllByHotel(UUID hotelId);

    String getAverageHotelRatings();

}
