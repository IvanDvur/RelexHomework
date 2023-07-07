package com.relex.ratingservice.repositories;

import com.relex.ratingservice.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {

    List<Rating> findByUserId(UUID userId);

    List<Rating> findByHotelId(UUID hotelId);

    @Query("SELECT r.hotelId, AVG(r.rating) AS averageRating FROM Rating r GROUP BY r.hotelId")
    List<Object[]> getAverageRatingsByHotel();

}
