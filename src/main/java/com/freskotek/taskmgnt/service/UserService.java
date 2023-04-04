package com.freskotek.taskmgnt.service;

import com.freskotek.taskmgnt.model.User;
import com.freskotek.taskmgnt.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final String SECRET = "Famous10@365#kwaminaotabil";
    private static final int EXPIRATION_TIME = 864_000_000; // 10 days

    public String generateToken(String id) {
        return Jwts.builder()
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

     public User getUserById(String id) {
        return userRepository.findById(id).get();
     }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public String getIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return BCrypt.checkpw(password, user.getPassword());
    }

    public String register(String username, String email, String password) {
        User existingUser = userRepository.findByUsername(username);

        if (existingUser != null) {
            return "Username already taken!";
        }
        existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            return "Email already taken!";
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, email, hashedPassword);
        userRepository.save(newUser);
        return "successful";
    }

    public boolean updateUser(String id, String username, String email) {
        User user = userRepository.findById(id).orElse(new User());

        if (user == null) {
            return false;
        }

        user.setUsername(username);
        user.setEmail(email);

        userRepository.save(user);
        return true;
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
