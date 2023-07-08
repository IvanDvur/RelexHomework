package com.relex.userservice.service;

import com.relex.userservice.domain.User;
import com.relex.userservice.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(UUID userId) throws ResourceNotFoundException;

    String getAverageHotelRatings();
}
