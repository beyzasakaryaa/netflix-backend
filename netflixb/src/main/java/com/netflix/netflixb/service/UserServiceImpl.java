package com.netflix.netflixb.service;

import com.netflix.netflixb.dto.UserRegisterDTO;
import com.netflix.netflixb.entity.Role;
import com.netflix.netflixb.entity.User;
import com.netflix.netflixb.repository.RoleRepository;
import com.netflix.netflixb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {

        if(userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new RuntimeException("Bu email zaten kullanılıyor.");
        }

        User user = new User();
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        // Rol ataması
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER bulunamadı"));

        // Eğer veri tabanında hiç kullanıcı yoksa, ilk kullanıcıyı admin yap
        if(userRepository.count() == 0) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("ROLE_ADMIN bulunamadı"));
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            user.setRoles(roles);
        } else {
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);
        }

        userRepository.save(user);
    }
}
