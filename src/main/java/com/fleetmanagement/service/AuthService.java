package com.fleetmanagement.service;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fleetmanagement.Repository.UserRepository;
import com.fleetmanagement.dto.AuthRequest;
import com.fleetmanagement.dto.AuthResponse;
import com.fleetmanagement.dto.RegisterRequest;
import com.fleetmanagement.entity.User;
import com.fleetmanagement.security.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtUtil;

    public AuthResponse register(RegisterRequest req) {
        User user = User.builder()
            .name(req.getName())
            .email(req.getEmail())
            .password(passwordEncoder.encode(req.getPassword()))
            .role(req.getRole())
            .createdAt(LocalDateTime.now())
            .build();

        userRepo.save(user);
        String token = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        ));
        return new AuthResponse(token);
    }

    public AuthResponse authenticate(AuthRequest req) {
        User user = userRepo.findByEmail(req.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        ));

        return new AuthResponse(token);
    }
}
