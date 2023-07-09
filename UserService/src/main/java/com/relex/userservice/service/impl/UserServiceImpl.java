package com.relex.userservice.service.impl;

import com.relex.userservice.domain.Hotel;
import com.relex.userservice.domain.Rating;
import com.relex.userservice.domain.User;
import com.relex.userservice.exceptions.InvalidDataFormatException;
import com.relex.userservice.exceptions.ResourceNotFoundException;
import com.relex.userservice.feignclients.HotelService;
import com.relex.userservice.feignclients.RatingService;
import com.relex.userservice.repositories.UserRepository;
import com.relex.userservice.service.UserService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RatingService ratingService;
    private final HotelService hotelService;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private final UserRepository userRepository;


    @Override
    public User saveUser(User user) throws InvalidDataFormatException {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<User> violation : violations) {
                System.out.println(violation.getMessage());
            }
            throw new InvalidDataFormatException("Данные имеют некорректный формат");
        }
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<Rating> allRatings = ratingService.getAllRatings();
        Map<UUID, List<Rating>> ratingMap = new HashMap<>();
        allRatings.forEach(x -> {
            UUID userId = x.getUserId();
            Hotel hotel = hotelService.getHotel(x.getHotelId().toString());
            x.setHotel(hotel);
            ratingMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(x);
        });
        List<User> users = userRepository.findAll();
        users.forEach(x -> x.setUserRatings(ratingMap.get(x.getUserId())));
        return users;
    }


    @Override
    public User getUser(UUID userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("Пользователь c id " + userId + " не найден"));
        List<Rating> ratingsForUser = ratingService.getRatingsForUser(userId.toString());
        user.setUserRatings(ratingsForUser);
        return user;
    }

    @Override
    public String getAverageHotelRatings() {
        String avgRatingsCsv = ratingService.getAverageRating();
        return avgRatingsCsv;
    }
}
