package com.netflix.netflixb.repository;

import com.netflix.netflixb.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByEmail(String email);
    boolean existsByEmailAndTitle(String email, String title);
}
