package com.netflix.netflixb.service;

import com.netflix.netflixb.entity.Favorite;
import com.netflix.netflixb.entity.User;
import com.netflix.netflixb.entity.Content;
import com.netflix.netflixb.repository.FavoriteRepository;
import com.netflix.netflixb.repository.UserRepository;
import com.netflix.netflixb.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContentRepository contentRepository;

    public void addFavorite(String username, Long contentId) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("İçerik bulunamadı"));

        // DOĞRU KONTROL (eski method yok!)
        if (favoriteRepository.existsByEmailAndTitle(user.getEmail(), content.getTitle())) {
            throw new RuntimeException("Bu içerik zaten favorilerde!");
        }

        Favorite favorite = new Favorite();
        favorite.setEmail(user.getEmail());
        favorite.setTitle(content.getTitle());
        favoriteRepository.save(favorite);
    }

    public List<Favorite> getFavorites(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        // user üzerinden değil, email ile arıyoruz!
        return favoriteRepository.findByEmail(user.getEmail());
    }
}
