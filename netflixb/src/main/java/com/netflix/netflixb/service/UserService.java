package com.netflix.netflixb.service;

import com.netflix.netflixb.dto.UserRegisterDTO;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);
}
