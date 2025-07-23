package com.netflix.netflixb.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthentication implements Authentication {

    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
    private boolean authenticated = true;

    // Yetkiyi dışarıdan almak için
    public JwtAuthentication(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities == null ? Collections.emptyList() : authorities;
    }

    // Eğer authority göndermeyeceksen eski constructor
    public JwtAuthentication(String username) {
        this(username, Collections.emptyList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return username;
    }
}
