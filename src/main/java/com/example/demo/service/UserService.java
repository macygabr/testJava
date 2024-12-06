package com.example.demo.service;

import com.example.demo.models.HttpException;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getInfo(String token) {
        Optional<User> user = userRepository.findByToken(token);
        if (user.isEmpty()) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        return user.get();
    }
}
