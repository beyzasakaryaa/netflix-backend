package com.netflix.netflixb.dto;

public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    // Eğer istersen (opsiyonel)
    public AuthResponse() {}

    // Setter'a gerek yok, sadece response içinse
}
