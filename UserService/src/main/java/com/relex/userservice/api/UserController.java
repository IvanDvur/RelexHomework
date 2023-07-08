package com.relex.userservice.api;

import com.relex.userservice.domain.User;
import com.relex.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User importedUser) {
        User user = userService.saveUser(importedUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    @CircuitBreaker(name = "ratingServiceBreaker", fallbackMethod = "ratingServiceFallback")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }


    public ResponseEntity<User> ratingServiceFallback(Exception ex) {
        logger.info("Сервис rating-service недоступен с ошибкой: {}", ex.getMessage());
        return new ResponseEntity<>(new User().builder()
                .userId(UUID.randomUUID())
                .username("Что-то пошло не так")
                .userRatings(null).email(null)
                .build(), HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingServiceBreaker", fallbackMethod = "ratingServiceFallback")
    public ResponseEntity<User> getAllUsers(@PathVariable("userId") String userId) {
        User user = userService.getUser(UUID.fromString(userId));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getAverage")
    public ResponseEntity<String> getAverageHotelRatings() {
        String avgCsv = userService.getAverageHotelRatings();
        return new ResponseEntity<>(avgCsv, HttpStatus.OK);
    }
}
