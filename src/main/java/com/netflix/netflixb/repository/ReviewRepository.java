package com.netflix.netflixb.repository;

import com.netflix.netflixb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTitle(String title);
    List<Review> findByEmail(String email);
}
