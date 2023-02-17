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

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final String SECRET = "secret_key";
    private static final int EXPIRATION_TIME = 864_000_000; // 10 days

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    // public User getUserById(String id) {
    // return userRepository.findById(id).get();
    // }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // public void deleteUser(String id) {
    // userRepository.deleteById(id);
    // }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // public User getUserByPhone(String phone){
    // return userRepository.findByPhone(phone);
    // }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return BCrypt.checkpw(password, user.getPassword());
    }

    public boolean register(String username, String email, String password) {
        User existingUser = userRepository.findByUsername(username);

        if (existingUser != null) {
            return false;
        }
        existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            return false;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, email, hashedPassword);
        userRepository.save(newUser);
        return true;
    }
}
