package com.netflix.netflixb.controller;

import com.netflix.netflixb.entity.Role;
import com.netflix.netflixb.entity.User;
import com.netflix.netflixb.repository.RoleRepository;
import com.netflix.netflixb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/register")
    public String registerUser(@RequestBody User userRequest) {

        // E-mail zaten var mı kontrol et
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            return "Email already registered!";
        }

        // Şifreyi şifrele
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);

        // Default rolü ekle (ROLE_USER)
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        if (userRole.isEmpty()) {
            return "Default role not found!";
        }

        Set<Role> roles = new HashSet<>();
        roles.add(userRole.get());
        userRequest.setRoles(roles);

        // Kullanıcıyı kaydet
        userRepository.save(userRequest);

        return "User registered successfully!";
    }
}
