package com.netflix.netflixb.controller;

import com.netflix.netflixb.dto.UserRegisterDTO;
import com.netflix.netflixb.dto.LoginRequestDTO;
import com.netflix.netflixb.entity.User;
import com.netflix.netflixb.dto.JwtResponse;
import com.netflix.netflixb.repository.RoleRepository;
import com.netflix.netflixb.repository.UserRepository;
import com.netflix.netflixb.security.JwtUtils;
import com.netflix.netflixb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;  // Register işlemi UserService ile olsun

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    // Register Endpoint - DTO ile
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        try {
            userService.registerUser(userRegisterDTO);
            return ResponseEntity.status(201).body("Kullanıcı başarıyla kayıt oldu.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Login Endpoint - DTO ile
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequestDTO.getEmail());

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Kullanıcı bulunamadı!");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Şifre hatalı!");
        }

        String jwt = jwtUtils.generateToken(user.getEmail());
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
