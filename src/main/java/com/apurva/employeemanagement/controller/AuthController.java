package com.apurva.employeemanagement.controller;

import com.apurva.employeemanagement.dto.RegisterResponseDTO;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import com.apurva.employeemanagement.dto.LoginRequestDTO;
import com.apurva.employeemanagement.dto.LoginResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.apurva.employeemanagement.service.JwtService;
import com.apurva.employeemanagement.dto.RegisterRequestDTO;
import com.apurva.employeemanagement.entity.User;
import com.apurva.employeemanagement.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserService userService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO loginRequest) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );

        String username = authentication.getName();

        String role = authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        String token = jwtService.generateToken(
                username,
                role
        );

        LoginResponseDTO response = new LoginResponseDTO();

        response.setToken(token);
        response.setUsername(username);
        response.setRole(role);

        return response;
    }

    @PostMapping("/register")
    public RegisterResponseDTO register(
           @Valid @RequestBody RegisterRequestDTO registerRequest) {

        User user = userService.registerUser(
                registerRequest.getUsername(),
                registerRequest.getPassword()
        );

        return new RegisterResponseDTO("User registered successfully",
                user.getUsername(),
                user.getRole()
        );
    }

}