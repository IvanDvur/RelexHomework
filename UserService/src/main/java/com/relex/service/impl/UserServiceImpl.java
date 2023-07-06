package com.relex.service.impl;

import com.relex.domain.User;
import com.relex.exceptions.InvalidDataFormatException;
import com.relex.exceptions.ResourceNotFoundException;
import com.relex.repositories.UserRepository;
import com.relex.service.UserService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public User saveUser(User user) throws InvalidDataFormatException{
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
        return userRepository.findAll();
    }

    @Override
    public User getUser(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Пользователь c id " + userId + " не найден"));
    }
}
