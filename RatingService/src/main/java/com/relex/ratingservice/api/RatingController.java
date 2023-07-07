package com.relex.ratingservice.api;

import com.relex.ratingservice.domain.Rating;
import com.relex.ratingservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating rating1 = ratingService.createRating(rating);
        return new ResponseEntity<>(rating1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return new ResponseEntity<>(ratingService.getAllRatings(),HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable(name = "userId") String userId){
        List<Rating> ratingsByUser = ratingService.getAllByUser(UUID.fromString(userId));
        return new ResponseEntity<>(ratingsByUser,HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsForHotel(@PathVariable(name = "hotelId") String hotelId) {
        return new ResponseEntity<>(ratingService.getAllByHotel(UUID.fromString(hotelId)),HttpStatus.OK);
    }
    @GetMapping(value = "/getAverage",produces = "text/csv")
    public String getAverageRatingsForHotels(){
        return ratingService.getAverageHotelRatings();
    }
}
