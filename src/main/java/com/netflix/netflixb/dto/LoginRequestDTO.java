package com.netflix.netflixb.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank(message = "E-posta alanı boş bırakılamaz.")
    private String email;

    @NotBlank(message = "Şifre alanı boş bırakılamaz.")
    private String password;

    // Getter ve Setter'lar
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
