package com.relex.api;

import com.relex.domain.User;
import com.relex.exceptions.InvalidDataFormatException;
import com.relex.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User importedUser){
        try {
            User user = userService.saveUser(importedUser);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }catch (InvalidDataFormatException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getAllUsers(@PathVariable String userId){
        User user  = userService.getUser(UUID.fromString(userId));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
