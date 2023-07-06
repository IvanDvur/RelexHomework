package com.relex.userservice.api;

import com.relex.userservice.domain.User;
import com.relex.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController{

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User importedUser) {
        User user = userService.saveUser(importedUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getAllUsers(@PathVariable("userId") String userId) {
        User user = userService.getUser(UUID.fromString(userId));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
