package com.netflix.netflixb.service;

import com.netflix.netflixb.dto.UserDTO;
import com.netflix.netflixb.dto.UserRegisterDTO;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);

    UserDTO getUserById(Long id);  // Yeni metod
}
