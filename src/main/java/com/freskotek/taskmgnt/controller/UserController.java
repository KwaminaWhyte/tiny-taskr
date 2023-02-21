package com.freskotek.taskmgnt.controller;

import com.freskotek.taskmgnt.model.User;
import com.freskotek.taskmgnt.service.UserService;
import com.freskotek.taskmgnt.util.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
TODO remove cors when serving frontend with spring
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public User getUserByToken(@RequestHeader("Authorization") String token) {
        System.out.println("getting current user...");
        String username = userService.getUsernameFromToken(token.replace("Bearer ", ""));
        return userService.getUserByUsername(username);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        System.out.println("login called...");
        if (!userService.validateUser(user.getUsername(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Generate token and return it to the client
        User currentUser = userService.getUserByUsername(user.getUsername());
        String token = userService.generateToken(user.getUsername());

        return ResponseEntity.ok(new LoginResponse(token, currentUser));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().toString());
        }

        boolean success = userService.register(user.getUsername(), user.getEmail(), user.getPassword());
        if (success) {
            return ResponseEntity.ok("User registered successfully.");
        } else {
            return ResponseEntity.badRequest().body("Username or email already exists.");
        }
    }

}
