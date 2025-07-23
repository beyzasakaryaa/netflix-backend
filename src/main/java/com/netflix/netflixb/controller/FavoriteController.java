package com.netflix.netflixb.controller;

import com.netflix.netflixb.entity.Favorite;
import com.netflix.netflixb.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    // Favori ekle (sadece USER rolü)
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{contentId}")
    public ResponseEntity<?> addFavorite(@PathVariable Long contentId, Principal principal) {
        favoriteService.addFavorite(principal.getName(), contentId);
        return ResponseEntity.ok("Favorilere eklendi!");
    }

    // Kullanıcının favorilerini getir (sadece USER rolü)
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<Favorite>> getFavorites(Principal principal) {
        List<Favorite> favorites = favoriteService.getFavorites(principal.getName());
        return ResponseEntity.ok(favorites);
    }
}
