package com.apurva.employeemanagement.service;

import com.apurva.employeemanagement.entity.User;
import com.apurva.employeemanagement.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(String username, String password, String role) {

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(
                username,
                encodedPassword,
                role
        );

        return userRepository.save(user);
    }

    public User registerUser(String username, String password) {

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(
                username,
                encodedPassword,
                "EMPLOYEE"
        );

        return userRepository.save(user);
    }
}