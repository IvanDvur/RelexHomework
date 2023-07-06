package com.relex.service;

import com.relex.domain.User;
import com.relex.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(UUID userId) throws ResourceNotFoundException;
}
