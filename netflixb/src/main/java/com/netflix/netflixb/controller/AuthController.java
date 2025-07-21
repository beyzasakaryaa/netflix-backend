package com.netflix.netflixb.controller;

import com.netflix.netflixb.entity.Role;
import com.netflix.netflixb.entity.User;
import com.netflix.netflixb.payload.response.JwtResponse;
import com.netflix.netflixb.repository.RoleRepository;
import com.netflix.netflixb.repository.UserRepository;
import com.netflix.netflixb.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;  // Burada jwtUtils'yi ekledik

    @PostMapping("/register")
    public String registerUser(@RequestBody User userRequest) {

        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            return "Email already registered!";
        }

        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);

        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        if (userRole.isEmpty()) {
            return "Default role not found!";
        }

        Set<Role> roles = new HashSet<>();
        roles.add(userRole.get());
        userRequest.setRoles(roles);

        userRepository.save(userRequest);

        return "User registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found!");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Incorrect password!");
        }

        String jwt = jwtUtils.generateToken(user.getEmail());

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

}
