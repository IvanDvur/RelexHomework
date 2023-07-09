package com.relex.userservice.feignclients;


import com.relex.userservice.domain.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "rating-service")
@Service
public interface RatingService {


    @GetMapping("/ratings/getAverage")
    String getAverageRating();

    @GetMapping("/ratings")
    List<Rating> getAllRatings();

    @GetMapping("/ratings/users/{userId}")
    List<Rating> getRatingsForUser(@PathVariable(name = "userId") String userId);
}
