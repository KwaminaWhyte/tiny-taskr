package com.freskotek.taskmgnt.controller;

import com.freskotek.taskmgnt.model.Task;
import com.freskotek.taskmgnt.model.User;
import com.freskotek.taskmgnt.repository.UserRepository;
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
    private UserRepository userRepository;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public User getUserByToken(@RequestHeader("Authorization") String token) {
        String id = userService.getIdFromToken(token.replace("Bearer ", ""));
        return userService.getUserById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        if (!userService.validateUser(user.getUsername(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid Credentials!");
        }

        // Generate token and return it to the client
        User currentUser = userService.getUserByUsername(user.getUsername());
        String token = userService.generateToken(currentUser.getId());

        return ResponseEntity.ok(new LoginResponse(token, currentUser));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().toString());
        }

        if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Please fill all the required fields.");
        }

        String response = userService.register(user.getUsername(), user.getEmail(), user.getPassword());
        if (response.equals("successful")) {
            User currentUser = userService.getUserByUsername(user.getUsername());
            String token = userService.generateToken(currentUser.getId());

            return ResponseEntity.ok(new LoginResponse(token, currentUser));
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestHeader("Authorization") String token, @RequestBody User user) {
        String id = userService.getIdFromToken(token.replace("Bearer ", ""));
        boolean success = userService.updateUser(id, user.getUsername(), user.getEmail());

        if (success) {
            User currentUser = userService.getUserById(id);
            return ResponseEntity.ok(currentUser);
        } else {
            return ResponseEntity.badRequest().body("User not found.");
        }
    }

    public boolean deleteUser(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return false;
        }

        userRepository.delete(user);
        return true;
    }

}
