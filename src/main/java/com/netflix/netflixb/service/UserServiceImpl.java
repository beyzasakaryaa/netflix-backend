package com.netflix.netflixb.service;

import com.netflix.netflixb.dto.UserDTO;
import com.netflix.netflixb.dto.UserRegisterDTO;
import com.netflix.netflixb.entity.Role;
import com.netflix.netflixb.entity.User;
import com.netflix.netflixb.repository.RoleRepository;
import com.netflix.netflixb.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new RuntimeException("Bu email zaten kullanılıyor.");
        }

        User user = new User();
        user.setEmail(userRegisterDTO.getEmail());
        // Kullanıcı adı kullanıyorsan aşağıdaki satırı açabilirsin
        // user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("ROLE_ADMIN bulunamadı."));
            roles.add(adminRole);
        } else {
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("ROLE_USER bulunamadı."));
            roles.add(userRole);
        }
        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return null;
        }
        User user = userOpt.get();
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
